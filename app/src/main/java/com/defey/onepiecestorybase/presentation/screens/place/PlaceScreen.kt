package com.defey.onepiecestorybase.presentation.screens.place

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.AvatarPlace
import com.defey.onepiecestorybase.domain.model.IslandPlace
import com.defey.onepiecestorybase.domain.model.RewardPlace
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.Constants.BUTTON_NEXT
import com.defey.onepiecestorybase.presentation.utils.Constants.DOWN_ISLAND
import com.defey.onepiecestorybase.presentation.utils.Constants.DURATION_POSTER
import com.defey.onepiecestorybase.presentation.utils.Constants.DURATION_POSTER_TEXT
import com.defey.onepiecestorybase.presentation.utils.Constants.GOAT_ISLAND
import com.defey.onepiecestorybase.presentation.utils.Constants.IMAGE_ARROW_BACK
import com.defey.onepiecestorybase.presentation.utils.Constants.IMAGE_ARROW_NEXT
import com.defey.onepiecestorybase.presentation.utils.Constants.IMAGE_CENTRED
import com.defey.onepiecestorybase.presentation.utils.Constants.IMAGE_CLOSE
import com.defey.onepiecestorybase.presentation.utils.Constants.NEXT
import com.defey.onepiecestorybase.presentation.utils.Constants.PATH_ASSETS_ISLAND
import com.defey.onepiecestorybase.presentation.utils.Constants.PATH_ASSETS_POSTER
import com.defey.onepiecestorybase.presentation.utils.Constants.PATH_ASSETS_SHIP
import com.defey.onepiecestorybase.presentation.utils.Constants.POSTER
import com.defey.onepiecestorybase.presentation.utils.Constants.POSTER_ALIGN_X
import com.defey.onepiecestorybase.presentation.utils.Constants.POSTER_ALIGN_Y
import com.defey.onepiecestorybase.presentation.utils.Constants.POSTER_ROTATE
import com.defey.onepiecestorybase.presentation.utils.Constants.POSTER_SIZE
import com.defey.onepiecestorybase.presentation.utils.Constants.POSTER_SIZE_PADDING
import com.defey.onepiecestorybase.presentation.utils.Constants.POSTER_SIZE_TEXT
import com.defey.onepiecestorybase.presentation.utils.Constants.REWARD
import com.defey.onepiecestorybase.presentation.utils.Constants.REWARD_POSTER
import com.defey.onepiecestorybase.presentation.utils.bounceClick
import com.defey.onepiecestorybase.presentation.utils.formatNumberWithSeparators
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.moveMarker
import ovh.plrapps.mapcompose.api.scale
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun PlaceScreen(
    state: PlaceUiState,
    onEvent: (PlaceUiEvent) -> Unit
) {
    val scope = rememberCoroutineScope()
    var animated by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(initialValue = 0f) }
    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = expanded, label = REWARD_POSTER)

    val sizeValue by transition.animateDp(
        transitionSpec = { tween(durationMillis = DURATION_POSTER) },
        label = POSTER_SIZE
    ) { screenState ->
        if (screenState) {
            200.dp
        } else {
            100.dp
        }
    }

    val sizePaddingValue by transition.animateDp(
        transitionSpec = { tween(durationMillis = DURATION_POSTER) },
        label = POSTER_SIZE_PADDING
    ) { screenState ->
        if (screenState) {
            34.dp
        } else {
            100.dp
        }
    }

    val textValue by transition.animateInt(
        transitionSpec = { tween(durationMillis = DURATION_POSTER_TEXT) },
        label = POSTER_SIZE_TEXT
    ) { screenState ->
        if (screenState) {
            14
        } else {
            7
        }
    }

    val rotateValue by transition.animateFloat(
        transitionSpec = { tween(durationMillis = DURATION_POSTER) },
        label = POSTER_ROTATE
    ) { screenState ->
        if (screenState) {
            0f
        } else {
            720f
        }
    }

    val alignValueX by transition.animateFloat(
        transitionSpec = { tween(durationMillis = DURATION_POSTER) },
        label = POSTER_ALIGN_X
    ) { isExpanded ->
        if (isExpanded) {
            0f
        } else {
            1f
        }
    }

    val alignValueY by transition.animateFloat(
        transitionSpec = { tween(durationMillis = DURATION_POSTER) },
        label = POSTER_ALIGN_Y
    ) { isExpanded ->
        if (isExpanded) {
            0f
        } else {
            -1f
        }
    }

    LaunchedEffect(animated) {
        if (state.animated) {
            rotation.animateTo(
                targetValue = if (!animated) 0f else 10f,
                animationSpec = tween(durationMillis = 1000),
            )
            animated = !animated
        }
    }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        MapUI(Modifier, state = state.stateMap) {
            addIslands(
                stateMap = state.stateMap,
                islands = state.islands,
                onClick = {
                    if (it != null)
                    onEvent(PlaceUiEvent.ClickIsland(it))
                }
            )
            addAvatar(
                state = state.stateMap,
                avatarInitial = state.avatars,
                avatarAnimation = state.avatarAnimate,
                rotation = rotation
            )
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_flag_luffy),
                    contentDescription = IMAGE_CENTRED,
                    modifier = Modifier
                        .size(70.dp)
                        .bounceClick {
                            onEvent(PlaceUiEvent.OnCenter(state.avatars.firstOrNull()?.name.orEmpty()))
                        }
                )
            }
        }

        if (state.rewards.isNotEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = BiasAlignment(alignValueX, alignValueY)
            ) {
                if (sizeValue == 200.dp) {
                    val pagerState = rememberPagerState(
                        initialPage = state.currentPage,
                        initialPageOffsetFraction = 0f,
                        pageCount = { state.rewards.size }
                    )
                    Column(
                        modifier = Modifier
                            .padding(bottom = 0.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.close_icon),
                            contentDescription = IMAGE_CLOSE,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(start = sizeValue, bottom = 8.dp)
                                .clickable {
                                    onEvent(PlaceUiEvent.SwipeReward(pagerState.currentPage))
                                    expanded = !expanded
                                }
                        )
                        Box(modifier = Modifier) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    RewardCard(
                                        reward = state.rewards[it],
                                        sizeValue = sizeValue,
                                        textValue = textValue,
                                        rotateValue = rotateValue
                                    ) {

                                    }
                                }
                            }

                            if (pagerState.canScrollBackward) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow),
                                    contentDescription = IMAGE_ARROW_BACK,
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .align(Alignment.CenterStart)
                                        .rotate(180f)
                                        .clickable {
                                            scope.launch {
                                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                            }
                                        }
                                )
                            }

                            if (pagerState.canScrollForward) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow),
                                    contentDescription = IMAGE_ARROW_NEXT,
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .align(Alignment.CenterEnd)
                                        .clickable {
                                            scope.launch {
                                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                            }
                                        }
                                )
                            }
                        }
                    }
                } else {
                    Column {
                        Spacer(modifier = Modifier.padding(top = sizePaddingValue))
                        RewardCard(
                            reward = state.rewards[state.currentPage],
                            sizeValue = sizeValue,
                            textValue = textValue,
                            rotateValue = rotateValue
                        ) {
                            expanded = !expanded
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.Bottom)
                .padding(bottom = 72.dp, end = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Box(modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.next_button),
                    contentDescription = BUTTON_NEXT,
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
                    text = if (state.timeStep == NEXT)
                        stringResource(id = R.string.next_place)
                    else state.timeStep,
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

fun addIslands(stateMap: MapState, islands: List<IslandPlace>, onClick: (Int?) -> Unit) {
    islands.forEach { island ->
        stateMap.addMarker(
            id = island.id,
            x = island.x,
            y = island.y,
            relativeOffset = Offset(-0.5f, -0.5f),
            zIndex = if (island.name == DOWN_ISLAND || island.name == GOAT_ISLAND) 1f else 0f
        ) {
            AsyncImage(
                model = PATH_ASSETS_ISLAND + island.nameImage,
                contentDescription = "",
                modifier = if (island.isEnabled)
                    Modifier
                        .scale(stateMap.scale)
                        .bounceClick {
                            Log.d("MyLog", "Click island ${island.name}")
                            onClick(island.placeId)
                        }
                else
                    Modifier
                        .scale(stateMap.scale)
                        .alpha(0.3f),
            )
        }
    }
}

fun addAvatar(
    state: MapState,
    avatarInitial: List<AvatarPlace>,
    avatarAnimation: Map<String, AvatarState>,
    rotation: Animatable<Float, AnimationVector1D>,
) {
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
                    .rotate(
                        avatarAnimation[avatarPlace.id]?.inclineAvatar ?: avatarPlace.inclineAvatar
                    )
                    .graphicsLayer {
                        rotationY =
                            avatarAnimation[avatarPlace.id]?.rotateAvatar
                                ?: avatarPlace.rotateAvatar
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

@Composable
fun RewardCard(
    reward: RewardPlace,
    sizeValue: Dp,
    rotateValue: Float,
    textValue: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(sizeValue)
            .rotate(rotateValue)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = onClick
            )
    ) {
        AsyncImage(
            model = PATH_ASSETS_POSTER,
            contentDescription = POSTER,
            modifier = Modifier
                .size(sizeValue)
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val widthImage = sizeValue * 56.6f / 100
            Spacer(
                modifier = Modifier
                    .padding(top = sizeValue / 4.83f)
            )
            AsyncImage(
                model = reward.image,
                contentDescription = REWARD,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = widthImage, height = widthImage * 77.4f / 100)
            )
            Text(
                text = reward.rewardType,
                style = OPTheme.typography.caption,
                color = OPTheme.colors.blackColor,
                fontWeight = FontWeight.ExtraBold,
                fontSize = textValue.sp
            )
            Text(
                text = reward.name.uppercase(Locale.ROOT),
                style = OPTheme.typography.caption,
                fontWeight = FontWeight.ExtraBold,
                color = OPTheme.colors.blackColor,
                fontSize = textValue.sp
            )
            Text(
                text = formatNumberWithSeparators(reward.reward),
                style = OPTheme.typography.caption,
                fontWeight = FontWeight.ExtraBold,
                color = OPTheme.colors.blackColor,
                fontSize = textValue.sp
            )
        }
    }
}