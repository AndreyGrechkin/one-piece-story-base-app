package com.defey.onepiecestorybase.presentation.screens.place

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.HiltComposeNode
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.navigation.appyxHiltViewModel

class PlaceNode(
    buildContext: BuildContext,
    application: Application,
    private val backStack: BackStack<NavTarget>
): HiltComposeNode(buildContext, application) {

    @Composable
    override fun View(modifier: Modifier){
        val viewModel = appyxHiltViewModel<PlaceViewModel>()
        PlaceScreen(
            stateMap = viewModel.stateMap,
            state = viewModel.state.value,
            onEvent = viewModel::onEvent,
            backstack = backStack
        )
    }
}