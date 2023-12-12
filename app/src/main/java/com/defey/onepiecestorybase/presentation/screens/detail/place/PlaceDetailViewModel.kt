package com.defey.onepiecestorybase.presentation.screens.detail.place

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.AvatarDetailPlace
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.domain.usecase.actionPlace.GetImageSizeUseCase
import com.defey.onepiecestorybase.domain.usecase.actionPlace.GetMangaPlaceUseCase
import com.defey.onepiecestorybase.domain.usecase.actionPlace.GetPlaceAvatar
import com.defey.onepiecestorybase.domain.usecase.actionPlace.GetPlaceAvatarUseCase
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getImageSizeUseCase: GetImageSizeUseCase,
    private val dataStore: DataStorePreferences,
    private val getMangaPlaceUseCase: GetMangaPlaceUseCase,
    private val getPlaceAvatarUseCase: GetPlaceAvatarUseCase,
) : AppViewModel<PlaceDetailUiState, PlaceDetailUiEvent>() {

    private val placeId: Int = savedStateHandle["placeId"] ?: 0
    private val imageIsland: String = savedStateHandle["imageIsland"] ?: ""

    private val _uiState = MutableStateFlow(PlaceDetailUiState(imageIsland = imageIsland))
    override val uiState: StateFlow<PlaceDetailUiState> = _uiState

    private var conWidth by mutableIntStateOf(0)
    private var conHeight by mutableIntStateOf(0)
    private var lastMangaId by mutableIntStateOf(0)
    private var mangaListId: List<Int> by mutableStateOf(emptyList())
    private var orient by mutableStateOf(ImageOrient.HORIZONTAL)
    private var avatarDetailPlaceList: List<AvatarDetailPlace> by mutableStateOf(emptyList())

    init {
        loadSizeImage()
    }

    override fun onEvent(event: PlaceDetailUiEvent) {
        when (event) {
            is PlaceDetailUiEvent.MaxSize -> {
                conWidth = event.maxWidth
                conHeight = event.maxHeight
            }

            is PlaceDetailUiEvent.OnScale -> {
                _uiState.update { it.copy(scale = event.scale) }
            }

            PlaceDetailUiEvent.ClickNext -> getNextItem()
            PlaceDetailUiEvent.ClickFlag -> navigateBack()
        }
    }

    private fun loadSizeImage() {
        getImageSizeUseCase(imageIsland).onEach { response ->
            if (response is Response.Success) {
                getPainterSize(response.value)
            }
        }.launchIn(viewModelScope)
    }

    private fun getPainterSize(painter: Pair<Int, Int>) {
        val width = painter.first.toFloat()
        val height = painter.second.toFloat()
        val widthCon = conWidth / width
        val heightCon = conHeight / height
        if (heightCon > widthCon) {
            orient = ImageOrient.HORIZONTAL
            val scale = conHeight / (height * widthCon)
            _uiState.update {
                it.copy(
                    orientImage = ImageOrient.HORIZONTAL,
                    scale = scale,
                    sizeImage = Pair(width, height)
                )
            }
        } else {
            orient = ImageOrient.VERTICAL
            val scale = conWidth / (width * heightCon)
            _uiState.update {
                it.copy(
                    orientImage = ImageOrient.VERTICAL,
                    scale = scale,
                    sizeImage = Pair(width, height)
                )
            }
        }
        observeManga()
    }

    private fun observeManga() {
        getMangaPlaceUseCase(placeId).combine(dataStore.readLastManga()) { response, lastManga ->
            if (response is Response.Success) {
                if (lastManga != null) {
                    lastMangaId = lastManga
                    mangaListId = response.value.map { it.id }
                    _uiState.update { state -> state.copy(manga = response.value.find { it.id == lastManga }) }
                    observeAvatar(lastManga)
                } else {
                    val firstManga = response.value.minOfOrNull { it.id } ?: 0
                    dataStore.saveLastManga(firstManga)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeAvatar(mangaId: Int) {
        getPlaceAvatarUseCase(
            GetPlaceAvatar(
                placeId,
                mangaId,
                avatarDetailPlaceList
            )
        ).onEach { response ->
            if (response is Response.Success) {
                avatarDetailPlaceList = response.value
                avatarOffset(response.value)
            }
        }.launchIn(viewModelScope)
    }

    private fun avatarOffset(avatar: List<AvatarDetailPlace>) {
        val startCounter = mutableMapOf<Pair<Float, Float>, Int>().withDefault { 0 }
        val endCounter = mutableMapOf<Pair<Float, Float>, Int>().withDefault { 0 }
        avatar.forEach { obj ->
            val startDigits =
                Pair(obj.startPosition.first().first, obj.startPosition.first().second)
            val endDigits = Pair(obj.endPosition.last().first, obj.endPosition.last().second)
            startCounter[startDigits] = startCounter.getValue(startDigits) + 1
            endCounter[endDigits] = endCounter.getValue(endDigits) + 1
        }

        val offsetAva = avatar.map {
            val countStart = startCounter[it.startPosition.first()]
            val countEnd = endCounter[it.endPosition.last()]
            val startCountIndex = when (countStart) {
                1, null -> 0
                else -> {
                    startCounter[it.startPosition.first()] =
                        startCounter.getValue(it.startPosition.first()) - 1
                    countStart - 1
                }
            }

            val endCountIndex = when (countEnd) {
                1, null -> 0
                else -> {
                    endCounter[it.endPosition.last()] =
                        endCounter.getValue(it.endPosition.last()) - 1
                    countEnd - 1
                }
            }
            AvatarOffset(
                id = it.id,
                nameAvatar = it.nameAvatar,
                placeId = it.placeId,
                mangaId = it.mangaId,
                imageAvatar = it.imageAvatar,
                startPosition = avatarPosition(it.startPosition, startCountIndex),
                endPosition = avatarPosition(it.endPosition, endCountIndex)
            )
        }
        _uiState.update { it.copy(offsetAvatar = offsetAva) }
    }

    private fun avatarPosition(
        avatarSizeList: List<Pair<Float, Float>>,
        countIndex: Int
    ): List<Offset> {
        val paddingAvatar = countIndex * 35f
        return avatarSizeList.map { avatarSize ->
            when (orient) {
                ImageOrient.HORIZONTAL -> {
                    val x =
                        (avatarSize.first * conWidth / uiState.value.sizeImage.first) * uiState.value.scale
                    val y = (avatarSize.second * conHeight / uiState.value.sizeImage.second)
                    val extraWidth = conWidth * (uiState.value.scale - 1)
                    val maxX = extraWidth / 2
                    val offX = if (x >= extraWidth) {
                        x - maxX
                    } else {
                        -(maxX - x)
                    }
                    Offset(x = offX + (paddingAvatar * uiState.value.scale), y = y)
                }

                ImageOrient.VERTICAL -> {
                    val x =
                        (avatarSize.first * conWidth / uiState.value.sizeImage.first) + (uiState.value.sizeImage.first * (uiState.value.scale - 1))
                    val y =
                        (avatarSize.second * conHeight / uiState.value.sizeImage.second) - (uiState.value.sizeImage.second * (uiState.value.scale - 1))
                    val extraWidth = conWidth * (uiState.value.scale - 1)
                    val maxX = extraWidth / 2
                    val offX = if (x >= extraWidth) {
                        x - maxX
                    } else {
                        -(maxX - x)
                    }
                    Offset(x = offX + paddingAvatar, y = y)
                }
            }
        }
    }

    private fun getNextItem() {
        viewModelScope.launch {
            val index = mangaListId.indexOf(lastMangaId)
            if (index != -1 && index < mangaListId.size - 1) {
                val nextElement = mangaListId[index + 1]
                dataStore.saveLastManga(nextElement)
            }
        }
    }
}

enum class ImageOrient {
    HORIZONTAL, VERTICAL
}