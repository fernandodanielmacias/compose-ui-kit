package io.github.ordonovus.sample.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.textfield.UiOutlinedTextField
import io.github.ordonovus.component.textfield.UiTextField
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiKitTheme

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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiDimensMedium),
        verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
    ) {
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
            showCharacterCount = true
        )

        UiOutlinedTextField(
            state = errorState,
            modifier = Modifier.fillMaxWidth(),
            label = {
                UiText("Error field")
            },
            isError = true,
            errorText = "The entered value is invalid"
        )

        UiOutlinedTextField(
            state = disabledState,
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            label = {
                UiText("Disabled field")
            }
        )

        UiOutlinedTextField(
            state = readOnlyState,
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            label = {
                UiText("Read-only field")
            }
        )
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
    UiKitTheme(
        darkTheme = false
    ) {
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
    UiKitTheme(
        darkTheme = true
    ) {
        UiTextFieldSample()
    }
}