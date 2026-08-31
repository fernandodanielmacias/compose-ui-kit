package io.github.ordonovus.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun UiKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        UiDarkColorScheme
    } else {
        UiLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = UiTypography,
        shapes = UiShapes,
        content = content
    )
}