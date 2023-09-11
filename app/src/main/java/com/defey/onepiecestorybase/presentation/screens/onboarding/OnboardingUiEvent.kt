package com.defey.onepiecestorybase.presentation.screens.onboarding

import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class OnboardingUiEvent : UiEvent {
    class SaveKey(val complete: Boolean) : OnboardingUiEvent()
    class NavigateTo(val naveTarget: NavTarget) : OnboardingUiEvent()
}
