package com.defey.onepiecestorybase.presentation.screens.place

import com.defey.onepiecestorybase.domain.model.AvatarPlace
import com.defey.onepiecestorybase.domain.model.IslandPlace
import com.defey.onepiecestorybase.domain.model.RewardPlace
import com.defey.onepiecestorybase.presentation.screens.UiState
import ovh.plrapps.mapcompose.ui.state.MapState

data class PlaceUiState(
    val stateMap: MapState,
    val islands: List<IslandPlace> = emptyList(),
    val avatars: List<AvatarPlace> = emptyList(),
    val avatarAnimate: Map<String, AvatarState> = mapOf(),
    val currentPage: Int = 0,
    val rewards: List<RewardPlace> = emptyList(),
    val animated: Boolean = false,
    val lastPlaceId: Int? = null,
    val timeStep: String = "",
    val isEnableNext: Boolean = true,
) : UiState

data class AvatarState(
    var coordinate: Pair<Double, Double> = Pair(0.0, 0.0),
    var rotateAvatar: Float = 0f,
    var inclineAvatar: Float = 0f
)
