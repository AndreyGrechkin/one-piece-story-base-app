package com.defey.onepiecestorybase.navigation.top

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.IconButton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigation() {
    TopAppBar(
        title = {
            Text(text = "dddd")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "back")
            }
        },

    )
}