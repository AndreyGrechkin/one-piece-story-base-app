package com.defey.onepiecestorybase.presentation.screens.info

import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor() : AppViewModel<InfoUiState, InfoUiEvent>() {

    private val _uiState = MutableStateFlow(InfoUiState())

    override val uiState: StateFlow<InfoUiState> = _uiState

    init {
        setupTopBar(showTopBar = true, title = UiText.StringResource(R.string.title_setting))
        setupBottomBar(isVisible = true)
        _uiState.update { it.copy(title = "Настройки") }
    }


    override fun onEvent(event: InfoUiEvent) {

    }


}