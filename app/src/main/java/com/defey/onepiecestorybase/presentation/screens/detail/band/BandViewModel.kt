package com.defey.onepiecestorybase.presentation.screens.detail.band

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.Response
import com.defey.onepiecestorybase.domain.usecase.band.GetBandDescriptionUseCase
import com.defey.onepiecestorybase.domain.usecase.band.GetBandPersonageUseCase
import com.defey.onepiecestorybase.domain.usecase.band.GetBandUseCase
import com.defey.onepiecestorybase.domain.usecase.band.GetShipBandUseCase
import com.defey.onepiecestorybase.domain.usecase.band.SendReadBandUseCase
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
class BandViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBandUseCase: GetBandUseCase,
    private val getBandDescriptionUseCase: GetBandDescriptionUseCase,
    private val getBandPersonageUseCase: GetBandPersonageUseCase,
    private val getShipBandUseCase: GetShipBandUseCase,
    private val sendReadBandUseCase: SendReadBandUseCase
) : AppViewModel<BandUiState, BandUiEvent>() {

    private val bandId: Int = savedStateHandle["bandId"] ?: 0
    private val _uiState = MutableStateFlow(BandUiState())
    override val uiState: StateFlow<BandUiState> = _uiState

    init {
        observeBand()
        observeDescription()
        observePersonage()
        observeShip()
        updateBand()
    }

    override fun onEvent(event: BandUiEvent) {
        when(event){
            BandUiEvent.CloseBand -> navigateBack()
        }
    }

    private fun observeBand() {
        getBandUseCase(bandId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update {
                    it.copy(
                        nameBand = response.value.nameBand,
                        imageBand = response.value.image
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeDescription() {
        getBandDescriptionUseCase(bandId).onEach { response ->
            if (response is Response.Success) {
                val description = response.value.mapNotNull { it.description }.joinToString("\n")
                _uiState.update { it.copy(description = description) }
            }
        }.launchIn(viewModelScope)
    }

    private fun observePersonage() {
        getBandPersonageUseCase(bandId).onEach { response ->
            if (response is Response.Success) {
                val capitan = response.value.findLast { it.career == "Капитан" }?.personageName
                _uiState.update {
                    it.copy(
                        personageList = response.value,
                        capitanName = capitan
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeShip() {
        getShipBandUseCase(bandId).onEach { response ->
            if (response is Response.Success) {
                _uiState.update { it.copy(shipList = response.value) }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateBand(){
        viewModelScope.launch {
            sendReadBandUseCase.execute(bandId)
        }
    }

}