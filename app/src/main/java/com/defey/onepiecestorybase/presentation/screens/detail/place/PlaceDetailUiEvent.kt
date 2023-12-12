package com.defey.onepiecestorybase.presentation.screens.detail.place

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class PlaceDetailUiEvent : UiEvent {
    class MaxSize(val maxWidth: Int, val maxHeight: Int) : PlaceDetailUiEvent()
    class OnScale(val scale: Float) : PlaceDetailUiEvent()
    data object ClickFlag : PlaceDetailUiEvent()
    data object ClickNext : PlaceDetailUiEvent()
}