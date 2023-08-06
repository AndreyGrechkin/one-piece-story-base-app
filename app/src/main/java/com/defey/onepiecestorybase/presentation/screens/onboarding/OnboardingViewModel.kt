package com.defey.onepiecestorybase.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.domain.repository.DataStorePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStore: DataStorePreferences
): ViewModel() {

    fun onEvent(event: OnboardingUiEvent) {
        when (event) {
           is OnboardingUiEvent.SaveKey -> {
                saveOnboardingKey(complete = event.complete)
            }
        }
    }

    private fun saveOnboardingKey(complete: Boolean) {
        viewModelScope.launch {
            dataStore.saveOnboardingComplete(complete)
        }
    }

}