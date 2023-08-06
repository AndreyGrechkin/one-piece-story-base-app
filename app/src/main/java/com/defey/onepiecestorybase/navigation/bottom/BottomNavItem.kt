package com.defey.onepiecestorybase.navigation.bottom

import androidx.compose.ui.graphics.vector.ImageVector
import com.defey.onepiecestorybase.navigation.NavTarget

data class BottomNavItem(
    val name: String,
    val route: NavTarget,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
