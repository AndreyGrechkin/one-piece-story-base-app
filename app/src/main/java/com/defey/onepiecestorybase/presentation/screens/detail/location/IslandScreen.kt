package com.defey.onepiecestorybase.presentation.screens.detail.location

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun IslandScreen(
    state: IslandUiState,
    onEvent: (IslandUiEvent) -> Unit,
){
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Экран ${state.title}")
    }
}