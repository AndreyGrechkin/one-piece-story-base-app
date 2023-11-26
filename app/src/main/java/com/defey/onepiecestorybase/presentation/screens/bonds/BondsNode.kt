package com.defey.onepiecestorybase.presentation.screens.bonds

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.HiltComposeNode
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.screens.AppScreen

class BondsNode(
    buildContext: BuildContext,
    application: Application,
    private val backStack: BackStack<NavTarget>
) : HiltComposeNode(buildContext, application) {
    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: BondsViewModel = viewModel()

        AppScreen(
            viewModel = viewModel,
            backStack = backStack
        ) { state, onEvent ->
            BondsScreen(
                state = state,
                onEvent = onEvent
            )
        }

    }
}