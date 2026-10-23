package io.github.ordonovus.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

/**
 * Applies the Compose UI Kit design system.
 *
 * Uses the default blue color schemes and semantic typography unless
 * custom values are provided. Typography customization is propagated
 * through the active Material theme, allowing UI Kit text components
 * to resolve their semantic styles dynamically.
 *
 * @param darkTheme Whether to use the dark color scheme.
 * @param colorScheme Color scheme applied to the theme.
 * @param typography Typography configuration applied to the theme.
 * @param content Composable content using the theme.
 */
@Composable
fun UiKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = if (darkTheme) UiDarkColorScheme else UiLightColorScheme,
    typography: Typography = UiTypography,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = UiShapes,
        content = content
    )
}