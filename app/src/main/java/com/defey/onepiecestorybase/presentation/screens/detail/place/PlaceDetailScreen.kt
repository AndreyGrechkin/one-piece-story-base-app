package com.defey.onepiecestorybase.presentation.screens.detail.place

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.screens.place.PlaceUiEvent
import com.defey.onepiecestorybase.presentation.utils.Constants
import com.defey.onepiecestorybase.presentation.utils.bounceClick

@Composable
fun PlaceDetailScreen(
    state: PlaceDetailUiState,
    onEvent: (PlaceDetailUiEvent) -> Unit
) {
    var scale by remember {
        mutableStateOf(1f)
    }
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    var painterWidth by remember {
        mutableStateOf(1f)
    }
    var painterHeight by remember {
        mutableStateOf(1f)
    }


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        scale = constraints.maxHeight / painterWidth
        val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
            scale = (scale * zoomChange).coerceIn(scale, 5f)
Log.d("MyLog", "con W: ${constraints.maxWidth}, con H: ${constraints.maxHeight}")
            val extraWidth = (scale - 1) * constraints.maxWidth
            val extraHeight = (scale - 4.63f) * constraints.maxHeight

            val maxX = extraWidth / 2
            val maxY = extraHeight / 2

            offset = Offset(
                x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY),
            )
            Log.d("MyLog", "maxX: ${maxX}, maxY: ${maxY}, ofset: $offset")
        }

        val  painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("file:///android_asset/other/konomi.png")
                .build(),
        )
        try {
            val width = painter.intrinsicSize.width
            val height = painter.intrinsicSize.height
            painterWidth = width
            painterHeight = height
            Log.d("MyLog", "width: $width, height: $height")
        }catch (e: Exception){
            Log.d("MyLog", "width Error: ${e.message}, height: ")
        }
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(state)
        )


   //     val height = painter.intrinsicSize.height


//Log.d("MyLog", "painter: ${painter.intrinsicSize.height}")
// Получение размеров изображения

//        AsyncImage(
//            model =  painter,
//            contentDescription = null,
//          //  contentScale = ContentScale.FillHeight,
//            modifier = Modifier
//                .fillMaxSize()
//                .graphicsLayer {
//                    scaleX = scale
//                    scaleY = scale
//                    translationX = offset.x
//                    translationY = offset.y
//                }
//                .transformable(state)
//        )
     //   val width = painter.intrinsicSize.width
  //      val height = painter.intrinsicSize.height
  //      Log.d("MyLog", "width: ${width}, height: ${height}")
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
                        //   onEvent(PlaceUiEvent.OnCenter(state.avatars.firstOrNull()?.name.orEmpty()))
                    }
            )
        }
    }
}