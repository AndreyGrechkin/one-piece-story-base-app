package com.defey.onepiecestorybase.presentation.screens.setting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(): ViewModel() {

    private val _state = mutableStateOf(SettingUiState())
    val state: State<SettingUiState> = _state

    init {
        _state.value = _state.value.copy(title = "Настройки")
    }


    fun onEvent(event: SettingUiEvent){

    }
}