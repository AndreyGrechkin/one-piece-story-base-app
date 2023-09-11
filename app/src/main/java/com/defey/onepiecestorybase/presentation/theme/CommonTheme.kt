package com.defey.onepiecestorybase.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

enum class OPSize {
    Smallest, Small, Medium, Big, Biggest
}

data class OPColors(
    val whiteColor: Color,
    val blackColor: Color,
    val grayColor: Color,
    val lightGrayColor: Color,
    val babyBlueEyesColor: Color,
    val pinkColor: Color
)

data class OPTypography(
    val heading: TextStyle,
    val subHeading: TextStyle,
    val body: TextStyle,
    val title: TextStyle,
    val caption: TextStyle,
    val segoe: TextStyle
)

object OPTheme {
    val colors: OPColors
        @Composable
        get() = LocalManagerColors.current

    val typography: OPTypography
        @Composable
        get() = LocalManagerTypography.current
}

val LocalManagerColors = staticCompositionLocalOf<OPColors> { error("No colors provided") }

val LocalManagerTypography =
    staticCompositionLocalOf<OPTypography> { error("No font provided") }