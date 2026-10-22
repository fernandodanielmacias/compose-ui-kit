package io.github.ordonovus.component.textfield

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

/**
 * Defines the colors used by built-in text field icons.
 *
 * @property action Color used by enabled action and leading icons.
 * @property disabled Color used by disabled icons.
 * @property error Color used by validation error indicators.
 */
@Immutable
data class UiTextFieldIconColors(
    val action: Color,
    val disabled: Color,
    val error: Color
)

/**
 * Provides default colors and access to the active text field icon palette.
 */
object UiTextFieldIconDefaults {

    /**
     * Creates a palette for built-in text field icons.
     *
     * @param action Color used by enabled icons.
     * @param disabled Color used by disabled icons.
     * @param error Color used by error indicators.
     * @return A text field icon color palette.
     */
    @Composable
    fun colors(
        action: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        disabled: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        error: Color = MaterialTheme.colorScheme.error
    ): UiTextFieldIconColors = UiTextFieldIconColors(
        action = action,
        disabled = disabled,
        error = error
    )

    /**
     * Returns the palette provided by the nearest icon theme, or the
     * Material 3 defaults when no custom palette is provided.
     */
    val current: UiTextFieldIconColors
        @Composable
        get() = LocalUiTextFieldIconColors.current ?: colors()
}

private val LocalUiTextFieldIconColors =
    compositionLocalOf<UiTextFieldIconColors?> { null }

/**
 * Provides shared icon colors to text fields within [content].
 *
 * A field can override these colors through its iconColors parameter.
 *
 * @param colors Palette applied to descendant text fields.
 * @param content Content receiving the palette.
 */
@Composable
fun UiTextFieldIconTheme(
    colors: UiTextFieldIconColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalUiTextFieldIconColors provides colors,
        content = content
    )
}