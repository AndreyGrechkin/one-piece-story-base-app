package com.defey.onepiecestorybase.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.activeElement
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.operation.replace
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.navigation.bottom.BottomNavigationBar
import com.defey.onepiecestorybase.navigation.top.TopBarNavigation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun <S : UiState, E : UiEvent> AppScreen(
    viewModel: AppViewModel<S, E>,
    backStack: BackStack<NavTarget>,
    content: @Composable BoxScope.(S, (E) -> Unit) -> Unit
) {
    val state = viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.navigationFlow.onEach {
            when (it) {
                is NavigationIntent.NavigateTo -> backStack.push(it.navTarget)
                is NavigationIntent.Replace -> backStack.replace(it.navTarget)
                NavigationIntent.NavigateBack -> backStack.pop()
            }
        }.launchIn(this)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = rememberScaffoldState(),
        topBar = {
            if (viewModel.topBarData.showTopBar)
                TopBarNavigation(
                    viewModel = viewModel,
                    searchAppBarState = viewModel.searchAppBarState.value,
                    searchTextState = viewModel.searchTextState,
                )
        },
        bottomBar = {
            if (viewModel.bottomBarData.isVisible)
                BottomNavigationBar(
                    items = viewModel.bottomList.value,
                    navController = backStack,
                    color = viewModel.bottomBarData.colorBackground,
                    onItemClick = { backStack.push(it.route) })
        },
        snackbarHost = {}
    ) { padding ->
        Box(
            modifier = if (backStack.activeElement == NavTarget.PlaceScreen) Modifier
                .fillMaxSize()
            else Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            content(state.value, viewModel::onEvent)
        }
    }
}