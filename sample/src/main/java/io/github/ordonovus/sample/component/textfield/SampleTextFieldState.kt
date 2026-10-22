package io.github.ordonovus.sample.component.textfield

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * Remembers a text field state initialized with localized sample content.
 *
 * Updates the field content when the localized value changes, such as
 * when the sample catalog switches between supported languages.
 *
 * This helper is intended for predefined demonstration values, not for
 * user-entered form data that must be preserved across locale changes.
 *
 * @param localizedValue Localized text displayed by the sample field.
 * @return A text field state synchronized with the localized value.
 */
@Composable
internal fun rememberLocalizedTextFieldState(
    localizedValue: String
): TextFieldState {
    val state = rememberTextFieldState(localizedValue)

    LaunchedEffect(localizedValue) {
        if (state.text.toString() != localizedValue) {
            state.setTextAndPlaceCursorAtEnd(localizedValue)
        }
    }

    return state
}
