package com.defey.onepiecestorybase.presentation.screens.place

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.data.remote.model.MapResponse
import com.defey.onepiecestorybase.domain.model.IslandPlace
import com.defey.onepiecestorybase.domain.repository.MapsRepository
import com.defey.onepiecestorybase.domain.repository.PlaceRepository
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.enableMarkerDrag
import ovh.plrapps.mapcompose.api.setPreloadingPadding
import ovh.plrapps.mapcompose.api.shouldLoopScale
import ovh.plrapps.mapcompose.ui.layout.Forced
import ovh.plrapps.mapcompose.ui.state.MapState
import javax.inject.Inject
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val repo: PlaceRepository,
    private val maps: MapsRepository,
) : AppViewModel<PlaceUiState, PlaceUiEvent>() {

    private val tileStreamProvider = maps.getFullMap()
    var p1x by mutableStateOf(0.5)
    var p1y by mutableStateOf(0.5)
    var rotateShip by mutableStateOf(0f)
    var inclineShip by mutableStateOf(0f)
    var coordinateList by mutableStateOf(
        listOf(
            Pair(0.5, 0.5),
            Pair(0.75, 0.51),
            Pair(0.85, 0.22),
            Pair(0.20, 0.43),
            Pair(0.5, 0.33)
        )
    )
    private val _state = MutableStateFlow(PlaceUiState())
 //   val state: State<PlaceUiState> = _state

    override val uiState: StateFlow<PlaceUiState> = _state



    var animated by mutableStateOf(false)
//    val rotation by mutableStateOf(Animatable(initialValue = 0f))
    val islands by mutableStateOf(listOf(
    IslandPlace(
        id = 0,
        x= 0.85,
        y = 0.51,
        isEnabled = true,
        name = "islan1"
    )))

    var markerCount = 0
    private var job: Job? = null
    private val spec = TweenSpec<Float>(2000, easing = FastOutSlowInEasing)
    val stateMap: MapState by mutableStateOf(
        MapState(1, 4096, 3072) {
            scale(1.2f)
            minimumScaleMode(Forced(1f))
            maxScale(5f)
        }.apply {
            setPreloadingPadding(2000)
            addLayer(tileStreamProvider)
            shouldLoopScale = true
        },
    )

    init {
        setupTopBar(title = UiText.StringResource(R.string.title_place), showTopBar = false)
        setupBottomBar(
            isVisible = true
//            Brush.linearGradient(
//            colors = listOf(Color.Red, Color.Yellow),
//            start = Offset(0f, 0f),
//            end = Offset(100f, 100f)
//        )
        )
    }

  override fun onEvent(event: PlaceUiEvent){

    }

    var place: MapResponse by mutableStateOf(
        MapResponse(
            0,
            null,
            null,
            0,
            emptyList(),
            null,
            null,
            null,
            null,
            null,
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )
    )

    fun getPlace(id: String) {
        viewModelScope.launch {
            try {
               val map = repo.getMapById(id)
                repo.syncMapById(id.toInt())
               place = map
            } catch (e: Exception) {
                Log.d("MyLog", "error: ${e.message}")
            }

        }
    }

    fun startAnimation() {
        animated = true
        job?.cancel()
        var p1xCon: Double
        var p1yCon: Double
        var hypCount = 0.0
      job =  viewModelScope.launch {
            coordinateList.forEachIndexed { index, coordinate ->
                p1xCon = coordinate.first
                p1yCon = coordinate.second
                if (coordinateList.size == index + 1) return@forEachIndexed
                val p2xCon: Double = coordinateList[index + 1].first
                val p2yCon: Double = coordinateList[index + 1].second
                rotateShip = if (p2xCon > p1xCon) 180f else 0f

                val xLine = if (p1xCon > p2xCon) p1xCon - p2xCon else p2xCon - p1xCon
                val yLine = if (p1yCon > p2yCon) p1yCon - p2yCon else p2yCon - p1yCon
                val hypLine = hypot(xLine, yLine)
                val cosX = xLine / hypLine
                val cosY = yLine / hypLine
                Log.d("MyLog", "cosx: ${  Math.toDegrees(cos(cosX)) }, cos: $cosX , sin: ${sin(cosX)}, sinY: ${Math.toDegrees(sin(cosY))} ")
                inclineShip = when {
                     p2xCon > p1xCon && p2yCon > p1yCon -> 10f
                    p2xCon < p1xCon && p2yCon < p1yCon -> 10f
                    p2xCon > p1xCon && p2yCon < p1yCon -> -10f
                    p2xCon < p1xCon && p2yCon > p1yCon -> -10f
                    else -> 0f
                }
                while (hypCount <= hypLine) {
                    hypCount += 0.001
                    p1x =
                        if (p1xCon > p2xCon) p1xCon - (cosX * hypCount) else p1xCon + (cosX * hypCount)
                    p1y =
                        if (p1yCon > p2yCon) p1yCon - (cosY * hypCount) else p1yCon + (cosY * hypCount)

                    delay(50)
                }
                hypCount = 0.0
            }
            animated = false
        }

    }
fun addIslandList(){

}
    fun addMarker() {
        stateMap.addMarker("marker$markerCount", 0.5, 0.5) {
            Icon(
                painter = painterResource(id = R.drawable.down_island_1),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = Color(0xCC2196F3)
            )
        }
        stateMap.enableMarkerDrag("marker$markerCount")
        markerCount++
    }
}