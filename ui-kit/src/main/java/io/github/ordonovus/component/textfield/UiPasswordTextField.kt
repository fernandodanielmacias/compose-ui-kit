package io.github.ordonovus.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldLabelScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import io.github.ordonovus.R

/**
 * Displays the action used to show or hide secure text field content.
 *
 * @param passwordVisible Whether the password is currently visible.
 * @param enabled Whether the visibility action is enabled.
 * @param contentDescription Accessibility description for the visibility
 * action.
 * @param onClick Called when the visibility action is selected.
 */
@Composable
private fun UiPasswordVisibilityButton(
    passwordVisible: Boolean,
    enabled: Boolean,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            painter = painterResource(
                if (passwordVisible) {
                    R.drawable.ic_visibility_off
                } else {
                    R.drawable.ic_visibility
                }
            ),
            contentDescription = contentDescription
        )
    }
}

/**
 * Displays an outlined secure text field for password input.
 *
 * The password is obscured by default and can be temporarily displayed using
 * the trailing visibility action. Changing its visibility does not modify the
 * value stored in [state].
 *
 * Secure text entry is provided by [BasicSecureTextField], which applies
 * password-oriented input behavior and restricts sensitive text operations.
 *
 * @param state State containing the password.
 * @param modifier Modifier applied to the text field.
 * @param enabled Whether the text field accepts user interaction.
 * @param isError Whether the text field should display an error state.
 * @param errorText Optional error message displayed when [isError] is `true`.
 * @param label Optional label displayed by the text field.
 * @param placeholder Optional placeholder displayed when the field is empty.
 * @param supportingText Optional supporting content displayed below the field
 * when no error message takes precedence.
 * @param maxLength Optional maximum number of characters accepted.
 * @param showCharacterCount Whether to display the current password length.
 * The counter is displayed only when [maxLength] is defined.
 * Disabled by default to avoid unnecessarily revealing password length.
 * @param showPasswordContentDescription Optional accessibility description
 * for the action that reveals the password. When `null`, the localized
 * default description is used.
 * @param hidePasswordContentDescription Optional accessibility description
 * for the action that hides the password. When `null`, the localized
 * default description is used.
 */
@Composable
fun UiOutlinedPasswordTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null,
    label: (@Composable TextFieldLabelScope.() -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    maxLength: Int? = null,
    showCharacterCount: Boolean = false,
    showPasswordContentDescription: String? = null,
    hidePasswordContentDescription: String? = null
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val resolvedShowPasswordContentDescription =
        showPasswordContentDescription
            ?: stringResource(R.string.ui_password_show)

    val resolvedHidePasswordContentDescription =
        hidePasswordContentDescription
            ?: stringResource(R.string.ui_password_hide)

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val obfuscationMode = if (passwordVisible) {
        TextObfuscationMode.Visible
    } else {
        TextObfuscationMode.System
    }

    val keyboardOptions = KeyboardOptions(
        autoCorrectEnabled = false,
        keyboardType = if (passwordVisible) {
            KeyboardType.PasswordVisible
        } else {
            KeyboardType.Password
        }
    )

    val inputTransformation = maxLength?.let {
        InputTransformation.maxLength(it)
    }

    val finalSupportingText = resolveTextFieldSupportingContent(
        state = state,
        maxLength = maxLength,
        showCharacterCount = showCharacterCount,
        isError = isError,
        errorText = errorText,
        supportingText = supportingText
    )

    val colors = UiTextFieldDefaults.outlinedColors()

    BasicSecureTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        inputTransformation = inputTransformation,
        keyboardOptions = keyboardOptions,
        textObfuscationMode = obfuscationMode,
        interactionSource = interactionSource,
        textStyle = LocalTextStyle.current.copy(
            color = if (enabled) {
                UiTextFieldDefaults.TextColor
            } else {
                UiTextFieldDefaults.DisabledTextColor
            }
        ),
        decorator = OutlinedTextFieldDefaults.decorator(
            state = state,
            enabled = enabled,
            lineLimits = TextFieldLineLimits.SingleLine,
            outputTransformation = null,
            interactionSource = interactionSource,
            label = label,
            placeholder = placeholder,
            trailingIcon = {
                UiPasswordVisibilityButton(
                    passwordVisible = passwordVisible,
                    enabled = enabled && state.text.isNotEmpty(),
                    contentDescription = if (passwordVisible) {
                        resolvedHidePasswordContentDescription
                    } else {
                        resolvedShowPasswordContentDescription
                    },
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                )
            },
            supportingText = finalSupportingText,
            isError = isError,
            colors = colors,
            container = {
                OutlinedTextFieldDefaults.Container(
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    shape = UiTextFieldDefaults.OutlinedShape,
                    focusedBorderThickness =
                        UiTextFieldDefaults.FocusedBorderThickness,
                    unfocusedBorderThickness =
                        UiTextFieldDefaults.UnfocusedBorderThickness
                )
            }
        )
    )
}