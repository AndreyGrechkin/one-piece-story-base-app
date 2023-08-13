package com.defey.onepiecestorybase.presentation.screens.info

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun InfoScreen(
    state: InfoUiState,
    onEvent: (InfoUiEvent) -> Unit
){
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Экран ${state.title}")
    }
}