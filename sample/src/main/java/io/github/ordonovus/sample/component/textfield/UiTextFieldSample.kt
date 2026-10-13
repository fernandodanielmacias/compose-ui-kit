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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.textfield.UiOutlinedPasswordTextField
import io.github.ordonovus.component.textfield.UiOutlinedTextField
import io.github.ordonovus.component.textfield.UiTextField
import io.github.ordonovus.component.textfield.UiTextFieldDefaults
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.SampleComponentHeader
import io.github.ordonovus.sample.ui.SamplePreview
import io.github.ordonovus.sample.ui.SampleSection
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium

/**
 * Displays the text field variants and states provided by Compose UI Kit.
 *
 * Demonstrates filled, outlined, multiline, error, disabled,
 * read-only, and secure password text fields.
 */
@Composable
fun UiTextFieldSample() {
    val filledState = rememberTextFieldState()
    val outlinedState = rememberTextFieldState()
    val limitedState = rememberTextFieldState()

    val errorState = rememberTextFieldState(
        stringResource(R.string.sample_text_fields_invalid_value)
    )

    val disabledState = rememberTextFieldState(
        stringResource(R.string.sample_text_fields_disabled_value)
    )

    val readOnlyState = rememberTextFieldState(
        stringResource(R.string.sample_text_fields_read_only_value)
    )

    val passwordState = rememberTextFieldState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        contentPadding = PaddingValues(UiDimensMedium),
        verticalArrangement = Arrangement.spacedBy(UiDimensLarge)
    ) {
        item {
            SampleComponentHeader(
                title = stringResource(R.string.sample_text_fields_title),
                description = stringResource(R.string.sample_text_fields_description)
            )
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_variants_title),
                description = stringResource(R.string.sample_text_fields_variants_description)
            ) {
                UiTextField(
                    state = filledState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_filled))
                    },
                    placeholder = {
                        UiText(stringResource(R.string.sample_text_fields_placeholder))
                    }
                )

                UiOutlinedTextField(
                    state = outlinedState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_outlined))
                    },
                    placeholder = {
                        UiText(stringResource(R.string.sample_text_fields_placeholder))
                    }
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_multiline_title),
                description = stringResource(R.string.sample_text_fields_multiline_description)
            ) {
                UiOutlinedTextField(
                    state = limitedState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_description_label))
                    },
                    placeholder = {
                        UiText(stringResource(R.string.sample_text_fields_maximum_placeholder))
                    },
                    maxLength = 50,
                    showCharacterCount = true,
                    lineLimits = UiTextFieldDefaults.multiLine()
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_validation_title),
                description = stringResource(R.string.sample_text_fields_validation_description)
            ) {
                UiOutlinedTextField(
                    state = errorState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_error_label))
                    },
                    isError = true,
                    errorText = stringResource(R.string.sample_text_fields_error_message)
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_states_title),
                description = stringResource(R.string.sample_text_fields_states_description)
            ) {
                UiOutlinedTextField(
                    state = disabledState,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_disabled_label))
                    }
                )

                UiOutlinedTextField(
                    state = readOnlyState,
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_read_only_label))
                    }
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_password_title),
                description = stringResource(R.string.sample_text_fields_password_description)
            ) {
                UiOutlinedPasswordTextField(
                    state = passwordState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_password_label))
                    },
                    maxLength = 20
                )
            }
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