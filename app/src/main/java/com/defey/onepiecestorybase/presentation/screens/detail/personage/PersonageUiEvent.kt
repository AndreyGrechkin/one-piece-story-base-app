package com.defey.onepiecestorybase.presentation.screens.detail.personage

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class PersonageUiEvent : UiEvent {
    data object ClosePersonage : PersonageUiEvent()
}
