package io.github.ordonovus.sample.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.component.button.UiButton
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.textfield.UiOutlinedPasswordTextField
import io.github.ordonovus.component.textfield.UiTextFieldIconDefaults
import io.github.ordonovus.sample.R
import io.github.ordonovus.theme.UiDimensMedium

/**
 * Demonstrates secure text field validation and leading icon customization.
 *
 * Includes interactive password validation, a custom leading icon,
 * and a password field without a leading icon.
 */
@Composable
internal fun UiTextFieldPasswordDemo() {
    val validationState = rememberTextFieldState()
    val customIconState = rememberTextFieldState()
    val noIconState = rememberTextFieldState()

    var validationRequested by remember { mutableStateOf(false) }
    var customIconError by remember { mutableStateOf(false) }

    val password = validationState.text.toString()

    val validationError = when {
        !validationRequested -> null
        password.isEmpty() ->
            R.string.sample_text_fields_password_required_error
        password.length < 8 ->
            R.string.sample_text_fields_password_length_error
        else -> null
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
    ) {
        UiOutlinedPasswordTextField(
            state = validationState,
            modifier = Modifier.fillMaxWidth(),
            label = {
                UiText(
                    stringResource(
                        R.string.sample_text_fields_password_validation_label
                    )
                )
            },
            supportingText = {
                UiText(
                    text = if (validationRequested && validationError == null) {
                        stringResource(
                            R.string.sample_text_fields_password_validation_success
                        )
                    } else {
                        stringResource(
                            R.string.sample_text_fields_password_validation_supporting
                        )
                    },
                    color = if (validationRequested && validationError == null) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            },
            isError = validationError != null,
            errorText = validationError?.let { stringResource(it) },
            maxLength = 20
        )

        UiButton(
            onClick = {
                validationRequested = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            UiText(
                stringResource(
                    R.string.sample_text_fields_password_validate_action
                )
            )
        }

        UiOutlinedPasswordTextField(
            state = customIconState,
            modifier = Modifier.fillMaxWidth(),
            isError = customIconError,
            errorText = if (customIconError) {
                stringResource(
                    R.string.sample_text_fields_password_custom_icon_error
                )
            } else {
                null
            },
            label = {
                UiText(
                    stringResource(R.string.sample_text_fields_password_custom_icon_label)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Security,
                    contentDescription = null,
                    tint = UiTextFieldIconDefaults.iconColor(
                        isError = customIconError
                    )
                )
            }
        )

        UiButton(
            onClick = {
                customIconError = !customIconError
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            UiText(
                stringResource(
                    if (customIconError) {
                        R.string.sample_text_fields_password_clear_error
                    } else {
                        R.string.sample_text_fields_password_show_error
                    }
                )
            )
        }

        UiOutlinedPasswordTextField(
            state = noIconState,
            modifier = Modifier.fillMaxWidth(),
            label = {
                UiText(
                    stringResource(
                        R.string.sample_text_fields_password_no_icon_label
                    )
                )
            },
            showDefaultLeadingIcon = false
        )
    }
}