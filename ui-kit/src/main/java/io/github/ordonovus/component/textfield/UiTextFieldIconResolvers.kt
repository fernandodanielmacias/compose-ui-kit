package io.github.ordonovus.component.textfield

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable

/**
 * Resolves the leading content displayed by a secure text field.
 *
 * Custom leading content takes precedence over the built-in lock icon.
 * When no custom content is provided, the default icon is displayed
 * only if [showDefaultLeadingIcon] is enabled.
 *
 * The built-in icon reflects the enabled and error states of the field.
 *
 * @param leadingIcon Optional custom leading content.
 * @param showDefaultLeadingIcon Whether to display the default lock icon
 * when custom leading content is not provided.
 * @param enabled Whether the associated text field is enabled.
 * @param isError Whether the associated text field is in an error state.
 * @param iconColors Colors used by the built-in lock icon.
 * @return The resolved leading composable, or `null` when hidden.
 */
internal fun resolvePasswordLeadingIcon(
    leadingIcon: (@Composable () -> Unit)?,
    showDefaultLeadingIcon: Boolean,
    enabled: Boolean,
    isError: Boolean,
    iconColors: UiTextFieldIconColors
): (@Composable () -> Unit)? {
    if (leadingIcon != null) {
        return leadingIcon
    }

    if (showDefaultLeadingIcon) {
        val lockIcon: @Composable () -> Unit = {
            UiPasswordLeadingIcon(
                enabled = enabled,
                isError = isError,
                iconColors = iconColors
            )
        }
        return lockIcon
    }

    return null
}

/**
 * Resolves the trailing content displayed by a text field.
 *
 * Custom trailing content takes precedence over the built-in error
 * indicator and clear action. The error indicator takes precedence
 * over the automatic clear action.
 *
 * @param state State containing the text field content.
 * @param enabled Whether the field is enabled.
 * @param readOnly Whether the field is read-only.
 * @param isError Whether the field is displaying an error.
 * @param showClearButton Whether the automatic clear action is enabled.
 * @param trailingIcon Optional custom trailing content.
 * @param iconColors Colors used by the built-in trailing icons.
 * @return The resolved trailing composable, or `null` when none is required.
 */
internal fun resolveTextFieldTrailingIcon(
    state: TextFieldState,
    enabled: Boolean,
    readOnly: Boolean,
    isError: Boolean,
    showClearButton: Boolean,
    trailingIcon: (@Composable () -> Unit)?,
    iconColors: UiTextFieldIconColors
): (@Composable () -> Unit)? {
    if (trailingIcon != null) {
        return trailingIcon
    }

    if (isError) {
        val errorIcon: @Composable () -> Unit = {
            UiTextFieldErrorIcon(
                iconColors = iconColors
            )
        }
        return errorIcon
    }

    if (showClearButton && enabled && !readOnly) {
        val clearButton: @Composable () -> Unit = {
            UiTextFieldClearButton(
                state = state,
                enabled = enabled,
                readOnly = readOnly,
                iconColors = iconColors
            )
        }
        return clearButton
    }

    return null
}