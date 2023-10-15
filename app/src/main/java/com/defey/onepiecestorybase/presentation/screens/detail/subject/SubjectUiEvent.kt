package com.defey.onepiecestorybase.presentation.screens.detail.subject

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class SubjectUiEvent : UiEvent {
    object SubjectClose : SubjectUiEvent()
}
