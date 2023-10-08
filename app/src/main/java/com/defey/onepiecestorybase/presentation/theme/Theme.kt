package com.defey.onepiecestorybase.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun OPTheme(
    textSize: OPSize = OPSize.Medium,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = OPTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                OPSize.Smallest -> 20.sp
                OPSize.Small -> 22.sp
                OPSize.Medium -> 26.sp
                OPSize.Big -> 28.sp
                OPSize.Biggest -> 30.sp
            },
            fontWeight = FontWeight.Bold,
            fontFamily = Cormorant
        ),
        subHeading = TextStyle(
            fontSize = when (textSize) {
                OPSize.Smallest -> 16.sp
                OPSize.Small -> 18.sp
                OPSize.Medium -> 20.sp
                OPSize.Big -> 22.sp
                OPSize.Biggest -> 24.sp
            },
            fontWeight = FontWeight.Bold,
            fontFamily = Cormorant
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                OPSize.Smallest -> 12.sp
                OPSize.Small -> 14.sp
                OPSize.Medium -> 16.sp
                OPSize.Big -> 18.sp
                OPSize.Biggest -> 20.sp
            },
            fontWeight = FontWeight.Normal,
            fontFamily = Cormorant
        ),
        title = TextStyle(
            fontSize = when (textSize) {
                OPSize.Smallest -> 14.sp
                OPSize.Small -> 16.sp
                OPSize.Medium -> 18.sp
                OPSize.Big -> 20.sp
                OPSize.Biggest -> 22.sp
            },
            fontWeight = FontWeight.Bold,
            fontFamily = Cormorant
        ),
        caption = TextStyle(
            fontSize = when (textSize) {
                OPSize.Smallest -> 10.sp
                OPSize.Small -> 12.sp
                OPSize.Medium -> 14.sp
                OPSize.Big -> 16.sp
                OPSize.Biggest -> 18.sp
            },
            fontFamily = Cormorant
        ),
        segoe = TextStyle(
            fontSize = when (textSize) {
                OPSize.Smallest -> 10.sp
                OPSize.Small -> 12.sp
                OPSize.Medium -> 14.sp
                OPSize.Big -> 16.sp
                OPSize.Biggest -> 18.sp
            },
            fontWeight = FontWeight.Bold,
            fontFamily = Segoe
        )
    )

    CompositionLocalProvider(
        LocalManagerColors provides colors,
        LocalManagerTypography provides typography,
        content = content
    )
}
