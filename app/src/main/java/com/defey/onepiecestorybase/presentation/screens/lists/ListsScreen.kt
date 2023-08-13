package com.defey.onepiecestorybase.presentation.screens.lists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.NavTarget

@Composable
fun ListsScreen(
    state: ListsUiState,
    onEvent: (ListsUiEvent) -> Unit,
){
 Column() {
      Text(text = "Экран ${state.title}, search: ${state.search}")
     Button(onClick = { onEvent(ListsUiEvent.Seteee) }) {
         Text(text = "pusdf")
     }
  }
}