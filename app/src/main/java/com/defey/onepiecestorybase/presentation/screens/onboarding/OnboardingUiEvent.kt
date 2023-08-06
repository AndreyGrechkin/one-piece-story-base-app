package com.defey.onepiecestorybase.presentation.screens.onboarding

sealed class OnboardingUiEvent {
    class SaveKey(val complete: Boolean) : OnboardingUiEvent()
}
