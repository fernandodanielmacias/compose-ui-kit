package io.github.ordonovus.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Creates the supporting content displayed below a text field.
 *
 * The supporting message is aligned to the start while the character
 * counter, when enabled, is aligned to the end. Error text takes
 * precedence over regular supporting content.
 *
 * @param state Current text field state used to calculate the character count.
 * @param maxLength Maximum number of characters allowed.
 * @param showCharacterCount Whether the character counter should be displayed.
 * @param isError Whether the text field is currently in an error state.
 * @param errorText Error message displayed when the field is in an error state.
 * @param supportingText Optional supporting content displayed below the field.
 * @return Supporting content, or `null` when no content is needed.
 */
@Composable
internal fun resolveTextFieldSupportingContent(
    state: TextFieldState,
    maxLength: Int?,
    showCharacterCount: Boolean,
    isError: Boolean,
    errorText: String?,
    supportingText: (@Composable () -> Unit)?
): (@Composable () -> Unit)? {
    val shouldShowError = isError && !errorText.isNullOrBlank()
    val shouldShowCounter = showCharacterCount && maxLength != null

    if (!shouldShowError && !shouldShowCounter && supportingText == null) {
        return null
    }

    return {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                when {
                    shouldShowError -> Text(text = errorText)
                    supportingText != null -> supportingText()
                }
            }

            if (shouldShowCounter) {
                Text(text = "${state.text.length} / $maxLength")
            }
        }
    }
}