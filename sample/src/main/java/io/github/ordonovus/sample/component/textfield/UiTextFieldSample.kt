package io.github.ordonovus.sample.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.textfield.UiOutlinedPasswordTextField
import io.github.ordonovus.component.textfield.UiOutlinedTextField
import io.github.ordonovus.component.textfield.UiTextField
import io.github.ordonovus.component.textfield.UiTextFieldDefaults
import io.github.ordonovus.sample.ui.SamplePreview
import io.github.ordonovus.theme.UiDimensMedium

/**
 * Displays the text field variants and states provided by Compose UI Kit.
 *
 * This sample verifies filled and outlined text fields, placeholders,
 * character limits, error states, disabled states, and read-only states.
 */
@Composable
fun UiTextFieldSample() {
    val filledState = rememberTextFieldState()
    val outlinedState = rememberTextFieldState()
    val limitedState = rememberTextFieldState()
    val errorState = rememberTextFieldState("Invalid value")
    val disabledState = rememberTextFieldState("Disabled value")
    val readOnlyState = rememberTextFieldState("Read-only value")
    val passwordState = rememberTextFieldState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        contentPadding = PaddingValues(
            start = UiDimensMedium,
            top = UiDimensMedium,
            end = UiDimensMedium
        ),
        verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
    ) {
        item {
            UiTextField(
                state = filledState,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    UiText("Filled field")
                },
                placeholder = {
                    UiText("Enter text")
                }
            )
        }

        item {
            UiOutlinedTextField(
                state = outlinedState,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    UiText("Outlined field")
                },
                placeholder = {
                    UiText("Enter text")
                }
            )
        }

        item {
            UiOutlinedTextField(
                state = limitedState,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    UiText("Description")
                },
                placeholder = {
                    UiText("Maximum 50 characters")
                },
                maxLength = 50,
                showCharacterCount = true,
                lineLimits = UiTextFieldDefaults.multiLine(),
            )
        }

        item {
            UiOutlinedTextField(
                state = errorState,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    UiText("Error field")
                },
                isError = true,
                errorText = "The entered value is invalid"
            )
        }

        item {
            UiOutlinedTextField(
                state = disabledState,
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                label = {
                    UiText("Disabled field")
                }
            )
        }

        item {
            UiOutlinedTextField(
                state = readOnlyState,
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                label = {
                    UiText("Read-only field")
                }
            )
        }

        item {
            UiOutlinedPasswordTextField(
                state = passwordState,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    UiText("Password")
                },
                maxLength = 20
            )
        }

    }
}

/**
 * Previews [UiTextFieldSample] using the light theme.
 */
@Preview(
    name = "Text Fields - Light",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTextFieldSampleLightPreview() {
    SamplePreview {
        UiTextFieldSample()
    }
}

/**
 * Previews [UiTextFieldSample] using the dark theme.
 */
@Preview(
    name = "Text Fields - Dark",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTextFieldSampleDarkPreview() {
    SamplePreview(
        darkTheme = true
    ) {
        UiTextFieldSample()
    }
}