package com.defey.onepiecestorybase.presentation.screens.splash

import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class SplashUiEvent : UiEvent {
    class NavigateTo(val naveTarget: NavTarget) : SplashUiEvent()
    object NavigateBack : SplashUiEvent()
}
