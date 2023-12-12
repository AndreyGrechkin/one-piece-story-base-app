package com.defey.onepiecestorybase.presentation.screens.detail.place

import androidx.compose.ui.geometry.Offset
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.presentation.screens.UiState

data class PlaceDetailUiState(
    val imageIsland: String,
    val manga: Manga? = null,
    val sizeImage: Pair<Float, Float> = Pair(0f, 0f),
    val scale: Float = 1f,
    val orientImage: ImageOrient = ImageOrient.HORIZONTAL,
    val offsetAvatar: List<AvatarOffset> = emptyList(),
) : UiState

data class AvatarOffset(
    val id: Int,
    val nameAvatar: String,
    val placeId: Int,
    val mangaId: Int,
    val imageAvatar: String,
    val startPosition: List<Offset>,
    val endPosition: List<Offset>,
)
