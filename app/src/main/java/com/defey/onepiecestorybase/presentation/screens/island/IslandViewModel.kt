package com.defey.onepiecestorybase.presentation.screens.island

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IslandViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(IslandUiState())
    val state: State<IslandUiState> = _state

    private val islandId: Int = savedStateHandle["islandId"] ?: 0

    init {
        _state.value = _state.value.copy(title = "Остров $islandId")
    }


    fun onEvent(event: IslandUiEvent){

    }
}