package com.defey.onepiecestorybase.presentation.screens.place

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.AvatarPlace
import com.defey.onepiecestorybase.domain.model.IslandPlace
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.Constants.PATH_ASSETS_ISLAND
import com.defey.onepiecestorybase.presentation.utils.Constants.PATH_ASSETS_SHIP
import com.defey.onepiecestorybase.presentation.utils.bounceClick
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.moveMarker
import ovh.plrapps.mapcompose.api.scale
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState

@SuppressLint("SuspiciousIndentation")
@Composable
fun PlaceScreen(
    state: PlaceUiState,
    onEvent: (PlaceUiEvent) -> Unit
) {
    var stateAnim = state.animated
    var animated by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(animated) {
        if (stateAnim) {
            rotation.animateTo(
                targetValue = if (!animated) 0f else 10f,
                animationSpec = tween(durationMillis = 1000),
            )
            animated = !animated
        }
    }
    Box(
        modifier = Modifier
    ) {
        MapUI(Modifier, state = state.stateMap) {
            addIslands(
                state.stateMap,
                state.islands,
                onEvent = onEvent
            )
            addAvatar(state.stateMap, state.avatars, state.avatarAnimate, rotation)
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalArrangement = Arrangement.End
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_flag_luffy),
                    contentDescription = "flag luffy",
                    modifier = Modifier
                        .size(70.dp)
                        .bounceClick {
                            onEvent(PlaceUiEvent.OnCenter(state.avatars.firstOrNull()?.name.orEmpty()))
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 72.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Box(modifier = Modifier) {
                    Image(
                        painter = painterResource(id = R.drawable.next_button),
                        contentDescription = "next button",
                        modifier =
                        if (state.isEnableNext) {
                            Modifier
                                .size(width = 100.dp, height = 45.dp)
                                .clickable {
                                    state.avatars.forEach {
                                        animated = true
                                        onEvent(PlaceUiEvent.StartAvatar(it.id, it.coordinateList))
                                    }
                                    onEvent(PlaceUiEvent.ClickNext)
                                }
                        } else {
                            Modifier
                                .size(width = 100.dp, height = 45.dp)
                                .alpha(0.5f)
                        }
                    )
                    Text(
                        text = "${state.timeStep}",
                        color = OPTheme.colors.whiteColor,
                        fontSize = 18.sp,
                        style = OPTheme.typography.segoe,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
            }
        }
    }
}

fun addIslands(stateMap: MapState, islands: List<IslandPlace>, onEvent: (PlaceUiEvent) -> Unit) {
    islands.forEach { island ->
        stateMap.addMarker(
            id = island.id,
            x = island.x,
            y = island.y,
            relativeOffset = Offset(-0.5f, -0.5f),
            zIndex = if (island.name == "down island" || island.name == "goat island") 1f else 0f
        ) {
            AsyncImage(
                model = PATH_ASSETS_ISLAND + island.nameImage,
                contentDescription = "",
                modifier = if (island.isEnabled)
                    Modifier
                        .scale(stateMap.scale)
                        .bounceClick { Log.d("MyLog", "Click island ${island.name}") }
                else
                    Modifier
                        .scale(stateMap.scale)
                        .alpha(0.3f),
            )
        }
    }
}

@Composable
fun addAvatar(
    state: MapState,
    avatarInitial: List<AvatarPlace>,
    avatarAnimation: Map<String, AvatarState>,
    rotation: Animatable<Float, AnimationVector1D>,
) {
    var direction = avatarAnimation
    avatarInitial.forEach { avatarPlace ->
        state.addMarker(
            id = avatarPlace.id,
            x = avatarPlace.coordinate.first,
            y = avatarPlace.coordinate.second,
            relativeOffset = Offset(-0.5f, -0.5f),
            zIndex = 2f
        ) {
            AsyncImage(
                model = PATH_ASSETS_SHIP + avatarPlace.nameImage,
                contentDescription = avatarPlace.name,
                modifier = Modifier
                    .scale(state.scale)
                    .rotate(direction[avatarPlace.id]?.inclineAvatar ?: avatarPlace.inclineAvatar)
                    .graphicsLayer {
                        rotationY =
                            direction[avatarPlace.id]?.rotateAvatar ?: avatarPlace.rotateAvatar
                    }
                    .rotate(if (avatarPlace.coordinateList.isNotEmpty()) rotation.value else 0f),
            )
        }
        state.moveMarker(
            avatarPlace.id,
            avatarAnimation[avatarPlace.id]?.coordinate?.first ?: avatarPlace.coordinate.first,
            avatarAnimation[avatarPlace.id]?.coordinate?.second ?: avatarPlace.coordinate.second
        )
    }
}