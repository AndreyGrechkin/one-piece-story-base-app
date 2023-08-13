package com.defey.onepiecestorybase.presentation.screens.place

import android.annotation.SuppressLint
import android.content.res.Resources
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.moveMarker
import ovh.plrapps.mapcompose.api.scale
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState

@SuppressLint("SuspiciousIndentation")
@Composable
fun PlaceScreen(
    stateMap: MapState,
    state: PlaceUiState,
    viewModel: PlaceViewModel = hiltViewModel(),
    onEvent: (PlaceUiEvent) -> Unit,

) {
    var stateAnim = viewModel.animated
    var animated by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(animated ) {

      if (stateAnim) {
          rotation.animateTo(
              targetValue = if (!animated) 0f else 10f,
              animationSpec = tween(durationMillis = 1000),
          )
          animated = !animated
      }
    }


Box(modifier = Modifier
        ) {

    val interactionSource  = remember { MutableInteractionSource()  }
            MapUI(Modifier, state = stateMap){
                stateMap.
                addMarker("parking1", 0.85361, 0.11006, Offset(-0.5f, -0.5f)) {
                    Image(
                        painter = painterResource(id = R.drawable.yotsuba_island_region_23),
                        contentDescription = null,
                        modifier = Modifier
                            .scale(stateMap.scale)
                            .bounceClick()
                            .alpha(0.3f)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { Log.d("MyLog", "Click island1") }
                            ),


                    )
                    Text(
                        text = "Yotsuba Island Region",
                        modifier = Modifier,
                        color = OPTheme.colors.blackColor,
                        style = OPTheme.typography.segoe,

                        )

                }

                stateMap.addMarker("parking3", 0.88533, 0.13664, Offset(-0.5f, -0.5f)) {
                    Image(
                        painter = painterResource(id = R.drawable.down_island_22),
                        contentDescription = null,
                        modifier = Modifier
                            .scale(stateMap.scale)
                            .clickable {
                                Log.d("MyLog", "Click island")
                            }
                            .bounceClick(),
                    )
                }

                stateMap.addMarker("parking6", 0.85673, 0.16284, Offset(-0.5f, -0.5f)) {
                    Image(
                        painter = painterResource(id = R.drawable.goat_island),
                        contentDescription = null,
                        modifier = Modifier
                            .scale(stateMap.scale)
                            .clickable {
                                Log.d("MyLog", "Click island")
                            }
                            .bounceClick(),
                    )
                }
                addShip(stateMap, viewModel, rotation)

                 }

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.down_island_1),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Green)

            )
            Image(
                painter = painterResource(
                    id = R.drawable.down_island_1
                ), contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Green),
                modifier = Modifier

                    .absoluteOffset(x = 1912.toDp().dp, y = 1500.toDp().dp)
                    .size(width = (106 / 3).dp, height = (80 / 3).dp)
                    .clickable {
                        Log.d("MyLog", "Click")
                    })
            Greeting("Android", onClick = {
                animated = true
                    viewModel.startAnimation()
            })
        }



    }


fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun addShip(state: MapState, viewModel: PlaceViewModel,  rotation: Animatable<Float, AnimationVector1D>){

    state.addMarker("parking4", viewModel.p1x, viewModel.p1y,) {
        Image(
            painter = painterResource(id = R.drawable.going_mary),
            contentDescription = null,
            modifier = Modifier
                .scale(state.scale)
                .rotate(viewModel.inclineShip)
                .graphicsLayer {
                    rotationY = viewModel.rotateShip
                }
                .clickable {
                    Log.d("MyLog", "Click ship")
                }
                .bounceClick()
                .rotate(rotation.value),
        )
    }
    state.moveMarker("parking4", viewModel.p1x, viewModel.p1y)
}
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    viewModel: PlaceViewModel = hiltViewModel()
) {

    val map = viewModel.place

    Column() {
        Text(
            text = "place: ${map.namePlace}",
            modifier = modifier
        )
        Text(
            text = "transcription: ${map.transcriptionJp}",
            modifier = modifier
        )

        Button(onClick = {viewModel.getPlace("1")}) {
            Text(text = "Жмак", modifier = modifier)

        }

                LazyColumn {
                    items(map.personageDescription) {
                Row {
                    Image(
                        painter = rememberImagePainter(data = it.image),
                        contentDescription = "avatar",
                        modifier = modifier
                            .width(60.dp)
                            .height(60.dp)
                    )
                    Column(modifier = modifier.padding(16.dp)) {
                        Text(text = "Description: ${it.description}")
                        Text(text = "Type: ${it.personageType}")
                        Text(text = "Fruit: ${it.fruitId}")
                        Text(text = "Personage: ${it.personageId}")

                    }
                }
            }
        }

    }

}

enum class ButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.70f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle

                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}