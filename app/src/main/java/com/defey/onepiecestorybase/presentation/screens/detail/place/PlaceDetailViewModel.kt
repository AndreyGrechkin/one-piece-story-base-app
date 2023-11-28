package com.defey.onepiecestorybase.presentation.screens.detail.place

import androidx.lifecycle.SavedStateHandle
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): AppViewModel<PlaceDetailUiState, PlaceDetailUiEvent>() {

    private val _uiState = MutableStateFlow(PlaceDetailUiState())
    override val uiState: StateFlow<PlaceDetailUiState> = _uiState
    private val placeId: Int = savedStateHandle["placeId"] ?: 0

    override fun onEvent(event: PlaceDetailUiEvent) {
       // when(event){}
    }
}