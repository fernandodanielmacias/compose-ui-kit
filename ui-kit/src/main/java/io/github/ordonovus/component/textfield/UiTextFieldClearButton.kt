package io.github.ordonovus.component.textfield

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.R

/**
 * Displays an action that clears the content of a text field.
 *
 * The action is displayed only when the text field contains text and can be
 * edited. Its appearance automatically follows the current Material theme
 * through the content color provided by [IconButton].
 *
 * Disabled and read-only fields do not expose the clear action.
 *
 * @param state State containing the text to clear.
 * @param enabled Whether the associated text field is enabled.
 * @param readOnly Whether the associated text field is read-only.
 * @param contentDescription Optional accessibility description for the clear
 * action. When `null`, the localized default description is used.
 */
@Composable
fun UiTextFieldClearButton(
    state: TextFieldState,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    contentDescription: String? = null
) {
    val resolvedContentDescription = contentDescription
        ?: stringResource(R.string.ui_text_field_clear)

    if (state.text.isNotEmpty() && enabled && !readOnly) {
        IconButton(
            onClick = {
                state.edit {
                    delete(0, length)
                }
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = resolvedContentDescription
            )
        }
    }
}

/**
 * Resolves the trailing icon for a text field.
 *
 * A custom trailing icon takes precedence over the default clear action.
 * The clear action is displayed only when the field contains text and
 * allows editing.
 *
 * @param state State containing the text field value.
 * @param enabled Whether the text field is enabled.
 * @param readOnly Whether the text field is read-only.
 * @param showClearButton Whether the default clear action is enabled.
 * @param trailingIcon Optional custom trailing icon.
 * @return The resolved trailing icon, or `null` when no icon is needed.
 */
@Composable
internal fun resolveTextFieldTrailingIcon(
    state: TextFieldState,
    enabled: Boolean,
    readOnly: Boolean,
    showClearButton: Boolean,
    trailingIcon: (@Composable () -> Unit)?
): (@Composable () -> Unit)? {
    return when {
        trailingIcon != null -> trailingIcon

        showClearButton &&
                state.text.isNotEmpty() &&
                enabled &&
                !readOnly -> {
            {
                UiTextFieldClearButton(
                    state = state,
                    enabled = enabled,
                    readOnly = readOnly
                )
            }
        }

        else -> null
    }
}
