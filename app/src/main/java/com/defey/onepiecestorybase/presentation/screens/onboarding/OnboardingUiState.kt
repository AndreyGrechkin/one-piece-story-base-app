package com.defey.onepiecestorybase.presentation.screens.onboarding

import com.defey.onepiecestorybase.domain.model.OnboardingPage
import com.defey.onepiecestorybase.presentation.screens.UiState

data class OnboardingUiState(
    val list: List<OnboardingPage>
) : UiState
