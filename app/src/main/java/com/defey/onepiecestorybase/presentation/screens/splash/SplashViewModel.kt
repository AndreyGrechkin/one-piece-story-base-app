package com.defey.onepiecestorybase.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.navigation.name
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
            is SplashUiEvent.NavigateTo -> {
                Log.d("MyLog", "eveb: ${event.naveTarget.name()}")
                navigateTo(event.naveTarget)
            }
            SplashUiEvent.NavigateBack -> navigateBack()
        }
    }

    private fun getKey() {
        dataStore.readOnboardingComplete()
            .onEach { isKey ->
                _uiState.update { it.copy(onboardingKey = isKey) }
                Log.d("MyLog", "flow: $isKey")
            }
            .launchIn(viewModelScope)
    }
}