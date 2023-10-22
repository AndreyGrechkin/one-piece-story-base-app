package com.defey.onepiecestorybase.presentation.screens.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.BandCompact
import com.defey.onepiecestorybase.domain.model.FruitCompact
import com.defey.onepiecestorybase.domain.model.LocationCompact
import com.defey.onepiecestorybase.domain.model.PersonageCompact
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.model.SubjectCompact
import com.defey.onepiecestorybase.domain.usecase.lists.GetBandListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetFruitListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetLocationListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetPersonageListUseCase
import com.defey.onepiecestorybase.domain.usecase.lists.GetSubjectListUseCase
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(
    private val getPersonageListUseCase: GetPersonageListUseCase,
    private val getBandListUseCase: GetBandListUseCase,
    private val getLocationListUseCase: GetLocationListUseCase,
    private val getSubjectListUseCase: GetSubjectListUseCase,
    private val getFruitListUseCase: GetFruitListUseCase
) : AppViewModel<ListsUiState, ListsUiEvent>() {

    private val _state = MutableStateFlow(ListsUiState())
    override val uiState: StateFlow<ListsUiState> = _state

    private var personageList: List<PersonageCompact> by mutableStateOf(emptyList())
    private var bandList: List<BandCompact> by mutableStateOf(emptyList())
    private var locationList: List<LocationCompact> by mutableStateOf(emptyList())
    private var subjectList: List<SubjectCompact> by mutableStateOf(emptyList())
    private var fruitList: List<FruitCompact> by mutableStateOf(emptyList())

    init {
        setupTopBar(
            showTopBar = true,
            title = UiText.StringResource(R.string.title_list),
            showBackButton = false,
            actionIconResId = R.drawable.ic_flag_luffy,
            onAction = { text ->
                _state.update { it.copy(search = text) }
                filter(text)

            }
        )

        setupBottomBar(isVisible = true)
        observePersonageList()
        observeBandList()
        observeLocationList()
        observeSubjectList()
        observeFruit()

    }

    override fun onEvent(event: ListsUiEvent) {
        when (event) {
            is ListsUiEvent.PersonageClick -> {
                navigateTo(NavTarget.PersonageScreen(event.personageId))
            }

            is ListsUiEvent.BandClick -> {
                navigateTo(NavTarget.BandScreen(event.bandId))
            }

            is ListsUiEvent.LocationClick -> {
                navigateTo(NavTarget.IslandScreen(event.locationId))
            }

            is ListsUiEvent.SubjectClick -> {
                navigateTo(NavTarget.SubjectScreen(event.subjectId))
            }

            is ListsUiEvent.FruitClick -> {
                navigateTo(NavTarget.FruitScreen(event.fruitId))
            }
        }
    }

    private fun filter(filterText: String) {
        filteredPersonage(filterText)
        filteredBand(filterText)
        filteredLocation(filterText)
        filteredSubject(filterText)
        filteredFruit(filterText)
    }

    private fun filteredPersonage(filterText: String) {
        val searchPersonage = personageList
            .filter {
                it.name.contains(filterText, true) ||
                        it.surname?.contains(filterText, true) ?: false ||
                        it.bandName?.contains(filterText, true) ?: false
            }
        _state.update { it.copy(personageList = searchPersonage) }
    }

    private fun filteredBand(filterText: String) {
        val searchBand = bandList
            .filter {
                it.bandName.contains(filterText, true) ||
                        it.bandType?.contains(filterText, true) ?: false
            }
        _state.update { it.copy(bandList = searchBand) }
    }

    private fun filteredLocation(filterText: String) {
        val searchLocation = locationList
            .filter {
                it.locationName?.contains(filterText, true) ?: false ||
                        it.placeName?.contains(filterText, true) ?: false ||
                        it.sea?.contains(filterText, true) ?: false
            }
        _state.update { it.copy(locationList = searchLocation) }
    }

    private fun filteredSubject(filterText: String) {
        val searchSubject = subjectList
            .filter {
                it.subjectName.contains(filterText, true)
            }
        _state.update { it.copy(subjectList = searchSubject) }
    }

    private fun filteredFruit(filterText: String) {
        val searchFruit = fruitList
            .filter {
                it.fruitName.contains(filterText, true) ||
                        it.fruitType?.contains(filterText, true) ?: false
            }
        _state.update { it.copy(fruitList = searchFruit) }
    }

    private fun observePersonageList() {
        getPersonageListUseCase().onEach { response ->
            if (response is Response.Success) {
                _state.update { it.copy(personageList = response.value) }
                personageList = response.value
            }
        }.launchIn(viewModelScope)
    }

    private fun observeBandList() {
        getBandListUseCase().onEach { response ->
            if (response is Response.Success) {
                _state.update { it.copy(bandList = response.value) }
                bandList = response.value
            }
        }.launchIn(viewModelScope)
    }

    private fun observeLocationList() {
        getLocationListUseCase().onEach { response ->
            if (response is Response.Success) {
                _state.update { it.copy(locationList = response.value) }
                locationList = response.value
            }
        }.launchIn(viewModelScope)
    }

    private fun observeSubjectList() {
        getSubjectListUseCase().onEach { response ->
            if (response is Response.Success) {
                _state.update { it.copy(subjectList = response.value) }
                subjectList = response.value
            }
        }.launchIn(viewModelScope)
    }

    private fun observeFruit() {
        getFruitListUseCase().onEach { response ->
            if (response is Response.Success) {
                _state.update { it.copy(fruitList = response.value) }
                fruitList = response.value
            }
        }.launchIn(viewModelScope)
    }
}