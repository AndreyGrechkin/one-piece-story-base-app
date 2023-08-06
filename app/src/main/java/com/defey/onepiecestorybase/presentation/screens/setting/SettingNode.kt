package com.defey.onepiecestorybase.presentation.screens.setting

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.HiltComposeNode
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.navigation.appyxHiltViewModel

class SettingNode(
    buildContext: BuildContext,
    application: Application,
    private val backStack: BackStack<NavTarget>
): HiltComposeNode(buildContext, application) {
    @Composable
    override fun View(modifier: Modifier){
        val viewModel = appyxHiltViewModel<SettingViewModel>()
        SettingScreen(
            state = viewModel.state.value,
            onEvent = viewModel::onEvent,
            backstack = backStack
        )
    }
}