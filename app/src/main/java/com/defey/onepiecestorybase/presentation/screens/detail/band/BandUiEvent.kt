package com.defey.onepiecestorybase.presentation.screens.detail.band

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class BandUiEvent : UiEvent {
    data object CloseBand : BandUiEvent()
}
