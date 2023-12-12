package com.defey.onepiecestorybase.presentation.screens.detail.place

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardBlock
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.Constants
import com.defey.onepiecestorybase.presentation.utils.bounceClick
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PlaceDetailScreen(
    state: PlaceDetailUiState,
    onEvent: (PlaceDetailUiEvent) -> Unit
) {

    var expanded by remember { mutableStateOf(true) }
    val mapAnimAvatar: MutableMap<Int, State<Offset>?> = remember { mutableStateMapOf() }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val scope = rememberCoroutineScope()
    var offsetAvatar by remember { mutableStateOf(Offset.Zero) }
    val transition = updateTransition(
        targetState = expanded,
        label = Constants.REWARD_POSTER
    )
    state.offsetAvatar.map { avatar ->
        val offState = transition.animateOffset(
            transitionSpec = { tween(durationMillis = 5000) },
            label = avatar.nameAvatar,
        ) { screenState ->
            if (screenState) {
                avatar.endPosition.first()
            } else {
                avatar.startPosition.first()
            }
        }
        mapAnimAvatar[avatar.id] = offState
    }
    state.offsetAvatar.map { avatar ->
        if (avatar.startPosition.size > 1) {
            List(avatar.startPosition.size) { index ->
                if (index != 0) {
                    val offState = transition.animateOffset(
                        transitionSpec = {
                            tween(
                                durationMillis = 5000,
                                delayMillis = index * 5000
                            )
                        },
                        label = avatar.nameAvatar,
                    ) { screenState ->
                        if (screenState) {
                            avatar.endPosition[index]
                        } else {
                            avatar.startPosition[index]
                        }
                    }
                    if (expanded) {
                        scope.launch {
                            delay(5000)
                            mapAnimAvatar[avatar.id] = offState
                        }
                    }
                }
            }

        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {

        onEvent(PlaceDetailUiEvent.MaxSize(constraints.maxWidth, constraints.maxHeight))
        val state2 = rememberTransformableState { _, panChange, _ ->
            when (state.orientImage) {
                ImageOrient.HORIZONTAL -> {
                    val extraWidth = (state.scale - 1) * constraints.maxWidth
                    val extraHeight = (state.scale - state.scale) * constraints.maxHeight

                    val maxX = extraWidth / 2
                    val maxY = extraHeight / 2

                    offset = Offset(
                        x = (offset.x + state.scale * panChange.x).coerceIn(-maxX, maxX),
                        y = (offset.y + state.scale * panChange.y).coerceIn(-maxY, maxY),
                    )
                    offsetAvatar = Offset(
                        x = (offsetAvatar.x + state.scale * panChange.x).coerceIn(-maxX, maxX),
                        y = offsetAvatar.y
                    )
                }

                ImageOrient.VERTICAL -> {
                    val extraWidth = (state.scale - state.scale) * constraints.maxWidth
                    val extraHeight = (state.scale - 1) * constraints.maxHeight

                    val maxX = extraWidth / 2
                    val maxY = extraHeight / 2

                    offset = Offset(
                        x = (offset.x + state.scale * panChange.x).coerceIn(-maxX, maxX),
                        y = (offset.y + state.scale * panChange.y).coerceIn(-maxY, maxY),
                    )
                    offsetAvatar = Offset(
                        x = (offsetAvatar.x),
                        y = (offsetAvatar.y + state.scale * panChange.y).coerceIn(-maxY, maxY)
                    )
                }
            }
        }

        AsyncImage(
            model = state.imageIsland,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    scaleX = state.scale
                    scaleY = state.scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(state2)
        )
        mapAnimAvatar.forEach { (key, offsetState) ->
            val avatarOffset = state.offsetAvatar.first { it.id == key }
            if (offsetState != null) {
                AsyncImage(
                    model = avatarOffset.imageAvatar,
                    contentDescription = null,
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = state.scale
                            scaleY = state.scale
                            translationX = offsetAvatar.x + offsetState.value.x
                            translationY = offsetAvatar.y + offsetState.value.y
                        }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_flag_luffy),
                contentDescription = Constants.IMAGE_CENTRED,
                modifier = Modifier
                    .size(70.dp)
                    .bounceClick {
                        onEvent(PlaceDetailUiEvent.ClickFlag)
                    }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            if (state.manga != null) {
                CardMangaBlock(manga = state.manga, modifier = Modifier.weight(2f))
            }

            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Box(modifier = Modifier) {
                    Image(
                        painter = painterResource(id = R.drawable.next_button),
                        contentDescription = Constants.BUTTON_NEXT,
                        modifier = Modifier
                            .size(width = 100.dp, height = 45.dp)
                            .clickable {
                                onEvent(PlaceDetailUiEvent.ClickNext)
                                expanded = false
                                expanded = true
                            }
                    )
                    Text(
                        text = stringResource(id = R.string.next_place),
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

@Composable
fun CardMangaBlock(manga: Manga, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    CardBlock(
        modifier = modifier
            .clickable {
                expanded = !expanded
            }
    ) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = manga.volume.toString(),
                style = OPTheme.typography.subHeading,
                modifier = Modifier
                    .padding(end = 4.dp)

            )
            Text(
                text = stringResource(R.string.title_chapter),
                style = OPTheme.typography.subHeading,
                modifier = Modifier
                    .padding(end = 4.dp)
            )
            Text(text = manga.chapter.toString(), style = OPTheme.typography.subHeading)
        }

        AnimatedVisibility(visible = expanded) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                drawLine(
                    color = Color.Black,
                    start = Offset(x = 0f, y = size.height / 2),
                    end = Offset(x = size.width, y = size.height / 2),
                    strokeWidth = 2f
                )
            }
            Text(
                text = manga.description.toString(),
                modifier = Modifier
                    .padding(8.dp)
                    .heightIn(max = 100.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = OPTheme.typography.body,
            )
        }
    }
}