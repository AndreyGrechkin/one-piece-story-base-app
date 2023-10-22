package com.defey.onepiecestorybase.presentation.screens.detail.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.usecase.location.GetLocationDescriptionUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetMangaLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetPersonageLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.location.GetSubjectLocationUseCase
import com.defey.onepiecestorybase.domain.usecase.location.SendReadLocationUseCase
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IslandViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLocationUseCase: GetLocationUseCase,
    private val getLocationDescriptionUseCase: GetLocationDescriptionUseCase,
    private val getMangaLocationUseCase: GetMangaLocationUseCase,
    private val getPersonageLocationUseCase: GetPersonageLocationUseCase,
    private val getSubjectLocationUseCase: GetSubjectLocationUseCase,
    private val sendReadLocationUseCase: SendReadLocationUseCase
) : AppViewModel<IslandUiState, IslandUiEvent>() {

    private val _uiState = MutableStateFlow(IslandUiState())
    override val uiState: StateFlow<IslandUiState> = _uiState
    private val locationId: Int = savedStateHandle["islandId"] ?: 0

    init {
        observeLocation()
        observeDescription()
        observeManga()
        observePersonage()
        observeSubject()
        updateLocation()
    }

    override fun onEvent(event: IslandUiEvent) {
        when (event) {
            IslandUiEvent.CloseLocation -> navigateBack()
        }
    }

    private fun observeLocation() {
        getLocationUseCase(locationId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(location = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateLocation(){
        viewModelScope.launch {
            sendReadLocationUseCase.execute(locationId)
        }
    }

    private fun observeDescription() {
        getLocationDescriptionUseCase(locationId).onEach { response ->
            if (response is Response.Success) {
                val description = response.value.mapNotNull { it.description }.joinToString("\n")
                val event = response.value.mapNotNull { it.event }.joinToString("\n")
                val image = response.value.findLast { it.image != null }?.image
                _uiState.update {
                    it.copy(
                        description = description,
                        event = event,
                        imageLocation = image
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observePersonage() {
        getPersonageLocationUseCase(locationId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(personageList = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeManga() {
        getMangaLocationUseCase(locationId).onEach { response ->
            if (response is Response.Success) {
                val manga = conversionString(response.value.map { it.chapter })
                val anime = conversionString(response.value.map { it.animeSeries })
                _uiState.update { it.copy(manga = manga, anime = anime) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeSubject() {
        getSubjectLocationUseCase(locationId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(subjectList = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun conversionString(list: List<String?>): String {
        val set = list.distinct()
        val result = StringBuilder()
        var i = 0
        while (i < set.size) {
            val current = set[i]
            if (current != null) {
                result.append(current)
                var j = i + 1
                while (j < set.size && set[j] is String && set[j - 1] is String && set[j]?.toIntOrNull() == set[j - 1]?.toIntOrNull()?.plus(1)) {
                    j++
                }
                if (j - i > 2) {
                    result.append(" - ")
                    result.append(set[j - 1])
                    if (j < set.size) {
                        result.append(", ")
                    }
                } else if (j - i == 2) {
                    result.append(", ")
                    result.append(set[j - 1])
                    if (j < set.size) {
                        result.append(", ")
                    }
                } else if (j - i == 1) {
                    if (j < set.size) {
                        result.append(", ")
                    } else {
                        result.append(" ")
                    }
                }
                i = j
            } else {
                i++
            }
        }
        return result.toString()
    }

}