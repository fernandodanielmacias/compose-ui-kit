package io.github.ordonovus.component.textfield

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

/**
 * Provides default values used by the text field components in Compose UI Kit.
 *
 * This object centralizes the visual configuration shared by filled and
 * outlined text fields. Material 3 defaults are used as the baseline so
 * components automatically inherit the active color scheme, including
 * light and dark themes.
 *
 * Error and disabled states are delegated to Material 3 defaults. Read-only
 * fields use dedicated colors provided by this object so they can remain
 * visually distinct from disabled fields while preserving readability.
 */
object UiTextFieldDefaults {

    /**
     * Default shape used by [UiTextField].
     */
    val Shape: Shape
        @Composable get() = TextFieldDefaults.shape

    /**
     * Default shape used by [UiOutlinedTextField].
     */
    val OutlinedShape: Shape
        @Composable get() = OutlinedTextFieldDefaults.shape

    /**
     * Creates the default colors for [UiTextField].
     *
     * Material 3 provides the enabled, focused, unfocused, disabled, and error
     * colors. When [readOnly] is `true`, this function applies dedicated
     * read-only text and container colors while keeping the field enabled.
     *
     * @param readOnly Whether the field is read-only.
     * @param readOnlyTextColor Text color used when the field is read-only.
     * @param readOnlyContainerColor Container color used when the field is
     * read-only.
     *
     * @return A [TextFieldColors] configuration for [UiTextField].
     */
    @Composable
    fun colors(
        readOnly: Boolean = false,
        readOnlyTextColor: Color = MaterialTheme.colorScheme.onSurface,
        readOnlyContainerColor: Color = MaterialTheme.colorScheme.surfaceVariant
    ): TextFieldColors {
        return if (readOnly) {
            TextFieldDefaults.colors(
                focusedTextColor = readOnlyTextColor,
                unfocusedTextColor = readOnlyTextColor,
                focusedContainerColor = readOnlyContainerColor,
                unfocusedContainerColor = readOnlyContainerColor
            )
        } else {
            TextFieldDefaults.colors()
        }
    }

    /**
     * Creates the default colors for [UiOutlinedTextField].
     *
     * Material 3 provides the enabled, focused, unfocused, disabled, and error
     * colors. When [readOnly] is `true`, the text and border use dedicated
     * read-only colors while the field remains enabled.
     *
     * @param readOnly Whether the field is read-only.
     * @param readOnlyTextColor Text color used when the field is read-only.
     * @param readOnlyBorderColor Border color used when the field is read-only.
     *
     * @return A [TextFieldColors] configuration for [UiOutlinedTextField].
     */
    @Composable
    fun outlinedColors(
        readOnly: Boolean = false,
        readOnlyTextColor: Color = MaterialTheme.colorScheme.onSurface,
        readOnlyBorderColor: Color = MaterialTheme.colorScheme.outlineVariant
    ): TextFieldColors {
        return if (readOnly) {
            OutlinedTextFieldDefaults.colors(
                focusedTextColor = readOnlyTextColor,
                unfocusedTextColor = readOnlyTextColor,
                focusedBorderColor = readOnlyBorderColor,
                unfocusedBorderColor = readOnlyBorderColor
            )
        } else {
            OutlinedTextFieldDefaults.colors()
        }
    }

}