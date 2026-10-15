package io.github.ordonovus.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Applies the Compose UI Kit design system.
 *
 * Uses the default blue color schemes unless a custom
 * color scheme is provided.
 *
 * @param darkTheme Whether to use the dark color scheme.
 * @param colorScheme Color scheme applied to the theme.
 * @param content Composable content using the theme.
 */
@Composable
fun UiKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = if (darkTheme) UiDarkColorScheme else UiLightColorScheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = UiTypography,
        shapes = UiShapes,
        content = content
    )
}
