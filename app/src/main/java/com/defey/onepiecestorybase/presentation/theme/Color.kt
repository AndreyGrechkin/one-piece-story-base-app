package com.defey.onepiecestorybase.presentation.theme

import androidx.compose.ui.graphics.Color

 val DarkColorPalette = OPColors(
    primaryTextColor = Color.White,
    primaryBackground = Color(0xFF090909),
    primaryText = Color(0xFFFFFFFF).copy(0.8f),
    secondaryBackground = Color(0xFF373737),
    secondaryText = Color(0xFF878787),
    tintColor = Color(0xFF7575FF),
    secondaryTintColor = Color(0xFF878787),
    strokeColor = Color(0xFF02ee4e),
    controlColor = Color(0xFF373737),
    errorColor = Color(0xFFFF5D62),
    snackBarColor = Color(0xFF493152),
    borderStrokeColor = Color(0xFF373737),
    secondTintColor = Color(0xFFB58AF3),
    secondBorderStrokeColor = Color(0xFFC0C0C0),
)

val LightColorPalette = OPColors(
    primaryTextColor = Color.White,
    primaryBackground = Color.White,
    primaryText = Color(0xFF000000),
    secondaryBackground = Color.White,
    secondaryText = Color(0xFF878787),
    tintColor = Color(0xFF504DA4),
    secondaryTintColor = Color(0xFF1A237E),
    strokeColor = Color(0xFF02EE4E),
    controlColor = Color(0xFFE32328),
    errorColor = Color(0xFFE32328),
    snackBarColor = Color(0xFF373737),
    borderStrokeColor = Color(0xFF000000),
    secondTintColor = Color(0xFF240057),
    secondBorderStrokeColor = Color(0xFFC0C0C0),
)