package com.defey.onepiecestorybase.presentation.screens.bonds

import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BondsViewModel @Inject constructor(): AppViewModel<BondsUiState, BondsUiEvent>() {

    private val _uiState = MutableStateFlow(BondsUiState())
    override val uiState: StateFlow<BondsUiState> = _uiState
    init {
        setupTopBar (showTopBar = true, title = UiText.StringResource(R.string.title_bond))
        setupBottomBar(isVisible = true)
        _uiState.update { it.copy(title = "Связи") }
    }

  override  fun onEvent(event: BondsUiEvent){

    }



}