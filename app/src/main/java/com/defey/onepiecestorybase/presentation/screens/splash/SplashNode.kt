package com.defey.onepiecestorybase.presentation.screens.splash

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.HiltComposeNode
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.navigation.appyxHiltViewModel

class SplashNode(
    buildContext: BuildContext,
    application: Application,
    private val backStack: BackStack<NavTarget>
) : HiltComposeNode(buildContext, application) {

    @Composable
    override fun View(modifier: Modifier) {
        Log.d("MyLog", "splash node")
        val viewModel = appyxHiltViewModel<SplashViewModel>()
        SplashScreen(
            state = viewModel.state.value,
            backstack = backStack
        )
    }
}