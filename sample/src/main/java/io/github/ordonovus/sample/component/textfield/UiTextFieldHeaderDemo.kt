package io.github.ordonovus.sample.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import io.github.ordonovus.component.button.UiButton
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.textfield.UiOutlinedTextField
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.feedback.LocalSampleSnackbarHostState
import io.github.ordonovus.sample.ui.layout.SampleDemoContainer
import io.github.ordonovus.sample.ui.feedback.showSampleMessage
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiTypographyLabel
import io.github.ordonovus.theme.UiTypographyTitleMedium

/**
 * Displays a compact, interactive text field demonstration
 * inside the sample component header.
 *
 * Demonstrates email validation using Compose UI Kit components.
 */
@Composable
fun UiTextFieldHeaderDemo() {
    val emailState = rememberTextFieldState()
    val snackbarHostState = LocalSampleSnackbarHostState.current
    val coroutineScope = rememberCoroutineScope()

    var validationRequested by remember {
        mutableStateOf(false)
    }

    val email = emailState.text.toString().trim()

    val isEmailValid = remember(email) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val showError = validationRequested && !isEmailValid

    val emailLabel = stringResource(R.string.text_field_header_email_label)
    val emailPlaceholder = stringResource(R.string.text_field_header_email_placeholder)
    val emailError = stringResource(R.string.text_field_header_email_error)
    val validateLabel = stringResource(R.string.text_field_header_validate)
    val successMessage = stringResource(R.string.text_field_header_success_message)

    SampleDemoContainer {
        Column(
            modifier = Modifier.padding(UiDimensMedium),
            verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
        ) {
            UiText(
                text = stringResource(R.string.sample_textfield_demo_title),
                style = UiTypographyTitleMedium,
                fontWeight = FontWeight.SemiBold
            )

            UiOutlinedTextField(
                state = emailState,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    UiText(emailLabel)
                },
                placeholder = {
                    UiText(emailPlaceholder)
                },
                isError = showError,
                errorText = if (showError) emailError else null,
                maxLength = 80,
                showClearButton = true
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                UiButton(
                    onClick = {
                        validationRequested = true

                        if (isEmailValid) {
                            snackbarHostState.showSampleMessage(
                                scope = coroutineScope,
                                message = successMessage
                            )
                        }
                    },
                    enabled = email.isNotBlank()
                ) {
                    UiText(validateLabel)
                }
            }
        }
    }
}