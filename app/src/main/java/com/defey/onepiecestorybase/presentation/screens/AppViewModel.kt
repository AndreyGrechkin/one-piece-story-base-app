package com.defey.onepiecestorybase.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.navigation.bottom.BottomNavItem
import com.defey.onepiecestorybase.navigation.top.SearchAppBarState
import com.defey.onepiecestorybase.presentation.utils.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class AppViewModel<S : UiState, E : UiEvent> : ViewModel() {

    private val gradientColors = listOf(
        Color(0x6600B5EE),
        Color(0x80A36C35)
    )

    private val grad = Brush.verticalGradient(
        colors = gradientColors,
        startY = 0f,
        endY = 150f,
    )

    var bottomBarData by mutableStateOf(BottomBarData(colorBackground = grad))
        private set

    var topBarData by mutableStateOf(TopBarData())
        private set

    private var updateScreenData by mutableStateOf(UpdateEventsScreen())


    var currentTab by mutableStateOf(0)

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    var searchTextState by mutableStateOf("")

    var bottomList = mutableStateOf(
        listOf(
            BottomNavItem(
                name = UiText.StringResource(R.string.maps),
                route = NavTarget.PlaceScreen,
                icon = R.drawable.earth_alt,
                isNew = updateScreenData.placeScreen
            ),
            BottomNavItem(
                name = UiText.StringResource(R.string.lists),
                route = NavTarget.ListsScreen,
                icon = R.drawable.script,
                isNew = updateScreenData.listScreen,
            ),
            BottomNavItem(
                name = UiText.StringResource(R.string.bonds),
                route = NavTarget.BondsScreen,
                icon = R.drawable.user_journey,
                isNew = updateScreenData.bondScreen
            ),
            BottomNavItem(
                name = UiText.StringResource(R.string.info),
                route = NavTarget.InfoScreen,
                icon = R.drawable.information,
                isNew = updateScreenData.infoScreen,
            ),
        )
    )


    private val _navigationFlow = MutableSharedFlow<NavigationIntent>(extraBufferCapacity = 1)
    val navigationFlow = _navigationFlow.asSharedFlow()

    abstract val uiState: StateFlow<S>
    abstract fun onEvent(event: E)

    fun setBottomBarUpdate(
        placeScreen: Boolean = updateScreenData.placeScreen,
        listScreen: Boolean = updateScreenData.listScreen,
        bondScreen: Boolean = updateScreenData.bondScreen,
        infoScreen: Boolean = updateScreenData.infoScreen
    ) {
        updateScreenData = UpdateEventsScreen(
            placeScreen = placeScreen,
            listScreen = listScreen,
            bondScreen = bondScreen,
            infoScreen = infoScreen
        )
    }

    fun setupTopBar(
        showTopBar: Boolean = topBarData.showTopBar,
        showBackButton: Boolean = topBarData.showBackButton,
        title: UiText = topBarData.title,
        actionIconResId: Int? = topBarData.actionIconResId,
        onAction: (String) -> Unit = topBarData.onAction
    ) {
        topBarData = TopBarData(
            showTopBar = showTopBar,
            showBackButton = showBackButton,
            title = title,
            actionIconResId = actionIconResId,
            onAction = onAction
        )
    }

    fun setupBottomBar(
        isVisible: Boolean = false,
        colorBackground: Brush = bottomBarData.colorBackground,
        update: UpdateEventsScreen = updateScreenData
    ) {
        bottomBarData = BottomBarData(
            isVisible = isVisible,
            colorBackground = colorBackground,
            update = update
        )
    }

    fun navigateTo(navTarget: NavTarget) {
        _navigationFlow.tryEmit(NavigationIntent.NavigateTo(navTarget))
    }

    fun navigateBack() {
        _navigationFlow.tryEmit(NavigationIntent.NavigateBack)
    }

    fun replaceNavigate(navTarget: NavTarget) {
        _navigationFlow.tryEmit(NavigationIntent.Replace(navTarget))
    }
}

data class BottomBarData(
    val isVisible: Boolean = false,
    val onAction: () -> Unit = {},
    val colorBackground: Brush,
    val update: UpdateEventsScreen = UpdateEventsScreen()
)

data class UpdateEventsScreen(
    val placeScreen: Boolean = false,
    val listScreen: Boolean = false,
    val bondScreen: Boolean = false,
    val infoScreen: Boolean = false
)

data class TopBarData(
    val showTopBar: Boolean = false,
    val showBackButton: Boolean = true,
    val title: UiText = UiText.EmptyString,
    val actionIconResId: Int? = null,
    val onAction: (String) -> Unit = {}
)


interface UiState
interface UiEvent

sealed class NavigationIntent {
    data class NavigateTo(
        val navTarget: NavTarget
    ) : NavigationIntent()

    data class Replace(val navTarget: NavTarget) : NavigationIntent()
    object NavigateBack : NavigationIntent()
}

