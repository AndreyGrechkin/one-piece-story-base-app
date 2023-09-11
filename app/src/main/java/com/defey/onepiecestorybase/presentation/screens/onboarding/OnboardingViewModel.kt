package com.defey.onepiecestorybase.presentation.screens.onboarding

import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.model.OnboardingPage
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStore: DataStorePreferences
) : AppViewModel<OnboardingUiState, OnboardingUiEvent>() {

    private val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    private val _uiState = MutableStateFlow(OnboardingUiState(list = pages))
    override val uiState: StateFlow<OnboardingUiState> = _uiState
    override fun onEvent(event: OnboardingUiEvent) {
        when (event) {
            is OnboardingUiEvent.SaveKey -> {
                saveOnboardingKey(complete = event.complete)
            }

            is OnboardingUiEvent.NavigateTo -> navigateTo(event.naveTarget)
        }
    }

    private fun saveOnboardingKey(complete: Boolean) {
        viewModelScope.launch {
            dataStore.saveOnboardingComplete(complete)
        }
    }
}