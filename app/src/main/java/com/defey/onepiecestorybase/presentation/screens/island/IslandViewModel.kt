package com.defey.onepiecestorybase.presentation.screens.island

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class IslandViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): AppViewModel<IslandUiState, IslandUiEvent>() {

    private val _uiState = MutableStateFlow(IslandUiState())
    override val uiState: StateFlow<IslandUiState> = _uiState
    private val islandId: Int = savedStateHandle["islandId"] ?: 0

    init {

        _uiState.update { it.copy(title = "Остров $islandId") }

    }


    override fun onEvent(event: IslandUiEvent){

    }



}