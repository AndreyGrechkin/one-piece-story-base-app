package com.defey.onepiecestorybase.presentation.screens.detail.fruit

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class FruitUiEvent : UiEvent {
    data object CloseFruit : FruitUiEvent()
}
