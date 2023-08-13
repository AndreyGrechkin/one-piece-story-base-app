package com.defey.onepiecestorybase.presentation.screens.bonds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navmodel.backstack.BackStack
import com.defey.onepiecestorybase.navigation.NavTarget

@Composable
fun BondsScreen(
    state: BondsUiState,
    onEvent: (BondsUiEvent) -> Unit,
){
 //   Box(contentAlignment = Alignment.Center) {
    //   Column {

     //   Text(text = "Экран ${state.title}")
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)){


            items(100){
                Text(text = "Item $it")
            }
        }
 //   }
  //  }
}