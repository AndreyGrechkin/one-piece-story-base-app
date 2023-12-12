package com.defey.onepiecestorybase.presentation.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.defey.onepiecestorybase.presentation.utils.Constants.DATE_TIME
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class ButtonState { Pressed, Idle }

fun Modifier.bounceClick(onClick: () -> Unit) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(
        if (buttonState == ButtonState.Pressed) 0.70f else 1f,
        label = ""
    )
    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

fun LocalDateTime.asString(): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME)
        this.format(formatter)
    } catch (e: Throwable) {
        ""
    }
}

fun String.asLocalDateTime(): LocalDateTime {
    return try {
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME)
        LocalDateTime.parse(this, formatter)
    } catch (e: Throwable) {
        LocalDateTime.now()
    }
}

fun formatNumberWithSeparators(number: Int): String {
    val numberFormat = NumberFormat.getInstance(Locale.US)
    return numberFormat.format(number).replace(",", ", ")
}