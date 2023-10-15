package com.defey.onepiecestorybase.presentation.screens.detail.fruit

import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.HiltComposeNode
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.navigation.appyxHiltViewModel
import com.defey.onepiecestorybase.presentation.screens.AppScreen

class FruitNode(
    buildContext: BuildContext,
    application: Application,
    private val backStack: BackStack<NavTarget>,
    defaultArgs: Bundle
) : HiltComposeNode(buildContext, application, defaultArgs) {
    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = appyxHiltViewModel<FruitViewModel>()

        AppScreen(
            viewModel = viewModel,
            backStack = backStack
        ) { state, onEvent ->
            FruitScreen(
                state = state,
                onEvent = onEvent
            )
        }

    }
}