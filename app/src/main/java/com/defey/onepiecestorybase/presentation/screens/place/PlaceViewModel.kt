package com.defey.onepiecestorybase.presentation.screens.place

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.domain.repository.MapsRepository
import com.defey.onepiecestorybase.domain.usecase.place.GetAvatarPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetIslandsUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetLastPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetNextTimeUseCase
import com.defey.onepiecestorybase.domain.usecase.place.GetPlaceRewardUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SyncMapParam
import com.defey.onepiecestorybase.domain.usecase.place.SyncMapUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SynchronizeIslandUseCase
import com.defey.onepiecestorybase.domain.usecase.place.SynchronizePersonageIslandUseCase
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.utils.Constants.NEXT
import com.defey.onepiecestorybase.presentation.utils.Constants.TIME
import com.defey.onepiecestorybase.presentation.utils.UiText
import com.defey.onepiecestorybase.presentation.utils.asString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.centerOnMarker
import ovh.plrapps.mapcompose.api.removeMarker
import ovh.plrapps.mapcompose.api.setPreloadingPadding
import ovh.plrapps.mapcompose.api.shouldLoopScale
import ovh.plrapps.mapcompose.ui.layout.Forced
import ovh.plrapps.mapcompose.ui.state.MapState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.hypot

@HiltViewModel
class PlaceViewModel @Inject constructor(
    maps: MapsRepository,
    private val syncIslandUseCase: SynchronizeIslandUseCase,
    private val getIslandsUseCase: GetIslandsUseCase,
    private val syncAvatarIslandUseCase: SynchronizePersonageIslandUseCase,
    private val getShipIslandUseCase: GetAvatarPlaceUseCase,
    private val syncMapUseCase: SyncMapUseCase,
    private val getLastPlaceUseCase: GetLastPlaceUseCase,
    private val dataStore: DataStorePreferences,
    private val getNextTimeUseCase: GetNextTimeUseCase,
    private val getPlaceRewardUseCase: GetPlaceRewardUseCase
) : AppViewModel<PlaceUiState, PlaceUiEvent>() {

    private val tileStreamProvider = maps.getFullMap()
    private val avatar = mutableStateMapOf<String, AvatarState>()
    private var time by mutableLongStateOf(0L)
    private val stateMap: MapState by mutableStateOf(
        MapState(1, 4096, 3072) {
            scale(1.2f)
            minimumScaleMode(Forced(1f))
            maxScale(4f)
        }.apply {
            setPreloadingPadding(2000)
            addLayer(tileStreamProvider)
            shouldLoopScale = true
        }
    )

    private val _uiState = MutableStateFlow(
        PlaceUiState(
            stateMap = stateMap,
            avatarAnimate = avatar
        )
    )
    override val uiState: StateFlow<PlaceUiState> = _uiState

    init {
        setupBottomBar(isVisible = true)
        observeLastPlace()
        observeIsland()
        observeAvatarPlace()
        observeReward()
        countDown()
    }

    override fun onEvent(event: PlaceUiEvent) {
        when (event) {
            is PlaceUiEvent.OnCenter -> onCenter(event.name)
            is PlaceUiEvent.StartAvatar -> startAnimation(event.id, event.list)
            PlaceUiEvent.ClickNext -> syncNextPlace()
            is PlaceUiEvent.SwipeReward -> rewardCurrentPager(event.currentPage)
        }
    }

    private fun observeLastPlace() {
        getLastPlaceUseCase().onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(lastPlaceId = response.value?.id) }
                time = response.value?.timeStep ?: 0
                if (response.value == null) {
                    syncNextPlace()
                    synchronizeIsland()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun synchronizeIsland() {
        syncIslandUseCase().onEach { response ->
            when (response) {
                Response.Loading -> {}
                is Response.Success -> {}
                is Response.Failure -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun observeIsland() {
        getIslandsUseCase().combine(getLastPlaceUseCase()) { response, lastPlace ->
            if (response is Response.Success) {
                if (lastPlace is Response.Success) {
                    val update = response.value.find { it.placeId == lastPlace.value?.id }?.name
                    uiState.value.stateMap.removeMarker(update.orEmpty())
                    _uiState.update { it.copy(islands = response.value) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun syncAvatarIsland(placeId: Int) {
        syncAvatarIslandUseCase(placeId).onEach { response ->
            when (response) {
                Response.Loading -> {}
                is Response.Success -> {}
                is Response.Failure -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun observeAvatarPlace() {
        getShipIslandUseCase().onEach { response ->
            if (response is Response.Success) {
                val responseAvatar = response.value.map { it.id }
                uiState.value.avatars.forEach { avatar ->
                    if (!responseAvatar.contains(avatar.id)) {
                        uiState.value.stateMap.removeMarker(avatar.id)
                    }
                }
                _uiState.update { it.copy(avatars = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeReward() {
        getPlaceRewardUseCase().onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(rewards = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun rewardCurrentPager(current: Int) {
        _uiState.update { it.copy(currentPage = current) }
    }

    private fun syncNextPlace() {
        saveNextTime(time)
        val lastPlaceId = uiState.value.lastPlaceId
        val placeId = if (lastPlaceId == null) 1 else lastPlaceId + 1
        syncAvatarIsland(placeId)
        syncMapUseCase(SyncMapParam(placeId)).onEach { response ->
            when (response) {
                is Response.Success -> {}
                is Response.Failure -> {}
                Response.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun onCenter(name: String) {
        viewModelScope.launch {
            stateMap.centerOnMarker(name, destScale = 1f)
        }
    }

    private fun startAnimation(id: String, coordinateList: List<Pair<Double, Double>>) {
        viewModelScope.launch {
            _uiState.update { it.copy(animated = true) }
            coordinateList.windowed(2, 1).forEach { (p1, p2) ->
                val rotateAvatar = if (p2.first > p1.first) 180f else 0f
                val inclineAvatar = when {
                    p2.first > p1.first && p2.second > p1.second -> 10f
                    p2.first < p1.first && p2.second < p1.second -> 10f
                    p2.first > p1.first && p2.second < p1.second -> -10f
                    p2.first < p1.first && p2.second > p1.second -> -10f
                    else -> 0f
                }
                val xLine = if (p1.first > p2.first) p1.first - p2.first else p2.first - p1.first
                val yLine =
                    if (p1.second > p2.second) p1.second - p2.second else p2.second - p1.second
                val hypLine = hypot(xLine, yLine)
                var hypCount = 0.0
                while (hypCount <= hypLine) {
                    hypCount += 0.001
                    val cosX = xLine / hypLine
                    val cosY = yLine / hypLine
                    val p1x =
                        if (p1.first > p2.first) p1.first - (cosX * hypCount) else p1.first + (cosX * hypCount)
                    val p1y =
                        if (p1.second > p2.second) p1.second - (cosY * hypCount) else p1.second + (cosY * hypCount)
                    avatar[id] = AvatarState(
                        inclineAvatar = inclineAvatar,
                        rotateAvatar = rotateAvatar,
                        coordinate = Pair(p1x, p1y)
                    )
                    delay(50)
                }
            }
            _uiState.update { it.copy(animated = false) }
        }
    }

    private fun countDown() {
        getNextTimeUseCase().onEach { response ->
            if (response is Response.Success) {
                createCountDownTimer(response.value)
            }
        }.launchIn(viewModelScope)
    }

    private fun createCountDownTimer(seconds: Long) {
        val countDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingSeconds = millisUntilFinished / 1000
                val localDateTime =
                    LocalDateTime.ofEpochSecond(remainingSeconds, 0, java.time.ZoneOffset.UTC)
                val formatter = DateTimeFormatter.ofPattern(TIME)
                val timeString = localDateTime.format(formatter)
                _uiState.update { it.copy(timeStep = timeString, isEnableNext = false) }
            }

            override fun onFinish() {
                _uiState.update { it.copy(timeStep = NEXT, isEnableNext = true) }
            }
        }
        countDownTimer.start()
    }

    private fun saveNextTime(timeStep: Long) {
        viewModelScope.launch {
            val dateNow = LocalDateTime.now()
            val datePref = dateNow.plusSeconds(timeStep).asString()
            dataStore.saveTimeStep(datePref)
        }
    }
}