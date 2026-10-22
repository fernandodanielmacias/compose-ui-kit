package io.github.ordonovus.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import io.github.ordonovus.theme.UiStrokeMedium
import io.github.ordonovus.theme.UiStrokeThick

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
     * Default border thickness used by outlined text fields when focused.
     */
    val FocusedBorderThickness: Dp = UiStrokeThick

    /**
     * Default border thickness used by outlined text fields when not focused.
     */
    val UnfocusedBorderThickness: Dp = UiStrokeMedium

    /**
     * Default indicator thickness used by filled text fields when focused.
     */
    val FocusedIndicatorThickness: Dp = UiStrokeThick

    /**
     * Default indicator thickness used by filled text fields when not focused.
     */
    val UnfocusedIndicatorThickness: Dp = UiStrokeMedium

    /**
     * Returns the default text color used by enabled text fields.
     *
     * The color is resolved from the active Material 3 color scheme so it
     * automatically adapts to light and dark themes.
     *
     * @return The text color for an enabled text field.
     */
    val TextColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSurface

    /**
     * Returns the default text color used by disabled text fields.
     *
     * @return The text color for a disabled text field.
     */
    val DisabledTextColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)

    /**
     * Returns the default text color used by read-only text fields.
     *
     * @return The text color for a read-only text field.
     */
    val ReadOnlyTextColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSurface

    /**
     * Color used by enabled text fields when they are not focused.
     *
     * Uses the active theme's primary color with reduced emphasis
     * to distinguish editable fields from disabled fields.
     */
    val UnfocusedAccentColor: Color
        @Composable get() = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f)

    /**
     * Creates keyboard options using Compose defaults while allowing common
     * text-input behavior to be customized from a single place.
     *
     * @param capitalization Capitalization behavior requested from the software
     * keyboard.
     * @param autoCorrectEnabled Whether autocorrection should be enabled.
     * A `null` value preserves the platform and IME default behavior.
     * @param keyboardType Keyboard layout requested from the software keyboard.
     *
     * @return A [KeyboardOptions] configuration for text field components.
     */
    fun keyboardOptions(
        capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
        autoCorrectEnabled: Boolean? = null,
        keyboardType: KeyboardType = KeyboardType.Text
    ): KeyboardOptions {
        return KeyboardOptions(
            capitalization = capitalization,
            autoCorrectEnabled = autoCorrectEnabled,
            keyboardType = keyboardType
        )
    }

    /**
     * Creates the default colors for [UiTextField].
     *
     * Enabled fields use a primary-colored indicator when unfocused.
     * Error and disabled colors remain controlled by Material 3.
     *
     * @param readOnly Whether the field is read-only.
     * @param readOnlyTextColor Text color used when read-only.
     * @param readOnlyContainerColor Container color used when read-only.
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
            TextFieldDefaults.colors(
                unfocusedIndicatorColor = UnfocusedAccentColor,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    /**
     * Creates the default colors for [UiOutlinedTextField].
     *
     * Enabled fields use a primary-colored border when unfocused.
     * Focused, error, and disabled states follow Material 3.
     *
     * @param readOnly Whether the field is read-only.
     * @param readOnlyTextColor Text color used when read-only.
     * @param readOnlyBorderColor Border color used when read-only.
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
            OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = UnfocusedAccentColor,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    /**
     * Creates multiline limits for text fields.
     *
     * This helper provides a concise way to configure text fields that need to
     * display multiple lines while keeping their vertical growth constrained.
     *
     * @param minLines Minimum number of visible text lines.
     * @param maxLines Maximum number of visible text lines.
     *
     * @return Line limits configured for multiline text input.
     */
    fun multiLine(
        minLines: Int = 3,
        maxLines: Int = 5
    ): TextFieldLineLimits {
        return TextFieldLineLimits.MultiLine(
            minHeightInLines = minLines,
            maxHeightInLines = maxLines
        )
    }

}