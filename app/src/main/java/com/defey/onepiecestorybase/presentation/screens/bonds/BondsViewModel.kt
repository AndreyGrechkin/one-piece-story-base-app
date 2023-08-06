package com.defey.onepiecestorybase.presentation.screens.bonds

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BondsViewModel @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(BondsUiState())
    val state: State<BondsUiState> = _state


    init {
        _state.value = _state.value.copy(title = "Связи")
    }

    fun onEvent(event: BondsUiEvent){

    }
}