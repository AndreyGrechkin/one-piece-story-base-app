package com.defey.onepiecestorybase.navigation.bottom

import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.utils.UiText

data class BottomNavItem(
    val name: UiText,
    val route: NavTarget,
    val icon: Int,
    val badgeCount: Int = 0,
    val isNew: Boolean = false
)
