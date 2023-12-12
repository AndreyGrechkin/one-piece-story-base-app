package com.defey.onepiecestorybase.presentation.screens.detail.location

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class IslandUiEvent : UiEvent {
    data object CloseLocation : IslandUiEvent()
}
