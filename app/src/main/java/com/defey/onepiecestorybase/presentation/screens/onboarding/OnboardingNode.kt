package com.defey.onepiecestorybase.presentation.screens.onboarding

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.HiltComposeNode
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.screens.AppScreen

class OnboardingNode(
    buildContext: BuildContext,
    application: Application,
    private val backStack: BackStack<NavTarget>
) : HiltComposeNode(buildContext, application) {

    @SuppressLint("SuspiciousIndentation")
    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OnboardingViewModel = viewModel()

        AppScreen(
            viewModel = viewModel,
            backStack = backStack
        ) { state, onEvent ->
            OnboardingScreen(
                state = state,
                onEvent = onEvent
            )
        }
    }
}