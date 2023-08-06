package com.defey.onepiecestorybase.presentation.screens.lists

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListsViewModel @Inject constructor(): ViewModel() {

    private val _state = mutableStateOf(ListsUiState())
    val state: State<ListsUiState> = _state


init {
    _state.value = _state.value.copy(title = "Списки")
}

    fun onEvent(event: ListsUiEvent){

    }
}