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
 * The action is displayed only when the field contains text and is editable.
 * Its icon uses the primary color from the active Material 3 color scheme,
 * independently of the field's validation state.
 *
 * Disabled and read-only fields do not expose the clear action.
 *
 * @param state State containing the text to clear.
 * @param enabled Whether the associated text field is enabled.
 * @param readOnly Whether the associated text field is read-only.
 * @param contentDescription Optional accessibility description for the clear
 * action. When `null`, the localized default description is used.
 * @param iconColors Colors applied to the clear action icon.
 */
@Composable
fun UiTextFieldClearButton(
    state: TextFieldState,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    contentDescription: String? = null,
    iconColors: UiTextFieldIconColors = UiTextFieldIconDefaults.current
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
                painter = painterResource(R.drawable.ic_cancel),
                contentDescription = resolvedContentDescription,
                tint = iconColors.action
            )
        }
    }
}