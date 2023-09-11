package com.defey.onepiecestorybase.presentation.screens.splash

import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStorePreferences
) : AppViewModel<SplashUiState, SplashUiEvent>() {

    private val _uiState = MutableStateFlow(SplashUiState())
    override val uiState: StateFlow<SplashUiState> = _uiState

    init {
        getKey()
    }

    override fun onEvent(event: SplashUiEvent) {
        when (event) {
            SplashUiEvent.NavigateBack -> navigateBack()
            is SplashUiEvent.NavigateTo -> {
                navigateTo(event.naveTarget)
            }
        }
    }

    private fun getKey() {
        dataStore.readOnboardingComplete()
            .onEach { isKey ->
                _uiState.update { it.copy(onboardingKey = isKey) }
            }
            .launchIn(viewModelScope)
    }
}