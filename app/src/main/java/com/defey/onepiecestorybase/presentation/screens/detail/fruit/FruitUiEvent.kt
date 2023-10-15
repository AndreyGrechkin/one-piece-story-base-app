package com.defey.onepiecestorybase.presentation.screens.detail.fruit

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class FruitUiEvent : UiEvent {
    object CloseFruit: FruitUiEvent()
}
