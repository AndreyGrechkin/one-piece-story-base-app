package com.defey.onepiecestorybase.presentation.screens.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.NavTarget

@Composable
fun SettingScreen(
    state: SettingUiState,
    onEvent: (SettingUiEvent) -> Unit,
    backstack: BackStack<NavTarget>
){
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Экран ${state.title}")
    }
}