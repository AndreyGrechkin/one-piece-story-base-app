package com.defey.onepiecestorybase.presentation.screens.place

import com.defey.onepiecestorybase.presentation.screens.UiEvent

sealed class PlaceUiEvent : UiEvent {
    class OnCenter(val name: String) : PlaceUiEvent()
    data object ClickNext : PlaceUiEvent()
    class StartAvatar(val id: String, val list: List<Pair<Double, Double>>) : PlaceUiEvent()
    class SwipeReward(val currentPage: Int) : PlaceUiEvent()
    class ClickIsland(val placeId: Int) : PlaceUiEvent()
}
