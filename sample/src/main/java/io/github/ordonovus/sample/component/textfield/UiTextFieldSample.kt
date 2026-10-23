package io.github.ordonovus.sample.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.button.UiButton
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.textfield.UiOutlinedTextField
import io.github.ordonovus.component.textfield.UiTextField
import io.github.ordonovus.component.textfield.UiTextFieldDefaults
import io.github.ordonovus.component.textfield.UiTextFieldIconDefaults
import io.github.ordonovus.component.textfield.UiTextFieldTransformations
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.feedback.LocalSampleSnackbarHostState
import io.github.ordonovus.sample.ui.feedback.showSampleMessage
import io.github.ordonovus.sample.ui.layout.SampleComponentHeader
import io.github.ordonovus.sample.ui.preview.SamplePreview
import io.github.ordonovus.sample.ui.layout.SampleSection
import io.github.ordonovus.sample.ui.theme.SampleCustomizationAccentColor
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiShapes
import io.github.ordonovus.theme.UiShapesCut
import io.github.ordonovus.theme.UiStrokeMedium
import io.github.ordonovus.theme.UiStrokeThick

/**
 * Displays the text field variants and states provided by Compose UI Kit.
 *
 * Demonstrates filled, outlined, multiline, interactive validation,
 * disabled, read-only, and secure password text fields.
 *
 * Also showcases built-in icons, supporting content, input and output
 * transformations, character limits, and visual customization.
 *
 * Password-specific examples are delegated to [UiTextFieldPasswordDemo].
 */
@Composable
fun UiTextFieldSample() {
    val filledState = rememberTextFieldState()
    val outlinedState = rememberTextFieldState()
    val limitedState = rememberTextFieldState()
    val iconState = rememberTextFieldState()
    val supportingState = rememberTextFieldState()
    val priceState = rememberTextFieldState()
    val weightState = rememberTextFieldState()
    val numericState = rememberTextFieldState()
    val formattedState = rememberTextFieldState()
    val customOutlinedState = rememberTextFieldState()
    val customFilledState = rememberTextFieldState()
    val verifiedState = rememberTextFieldState()

    val snackbarHostState = LocalSampleSnackbarHostState.current
    val coroutineScope = rememberCoroutineScope()

    val searchMessage = stringResource(R.string.sample_text_fields_search_feedback)

    val validationState = rememberTextFieldState()
    var validationRequested by remember { mutableStateOf(false) }
    val validationValue = validationState.text.toString()

    val validationError = when {
        !validationRequested -> null
        validationValue.isEmpty() ->
            R.string.sample_text_fields_required_error
        validationValue.length != 6 ->
            R.string.sample_text_fields_invalid_code_error
        else -> null
    }

    val disabledState = rememberLocalizedTextFieldState(
        localizedValue = stringResource(
            R.string.sample_text_fields_disabled_value
        )
    )

    val readOnlyState = rememberLocalizedTextFieldState(
        localizedValue = stringResource(
            R.string.sample_text_fields_read_only_value
        )
    )

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
                description = stringResource(R.string.sample_text_fields_description),
                demo = {
                    UiTextFieldHeaderDemo()
                }
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
                    iconColors = UiTextFieldIconDefaults.colors(
                        action = MaterialTheme.colorScheme.tertiary
                    ),
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
                title = stringResource(R.string.sample_text_fields_icons_title),
                description = stringResource(R.string.sample_text_fields_icons_description)
            ) {
                UiOutlinedTextField(
                    state = iconState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_search_label))
                    },
                    placeholder = {
                        UiText(stringResource(R.string.sample_text_fields_search_placeholder))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    showClearButton = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    onKeyboardAction = KeyboardActionHandler {
                        snackbarHostState.showSampleMessage(
                            scope = coroutineScope,
                            message = searchMessage
                        )
                    }
                )

                UiTextField(
                    state = supportingState,
                    modifier = Modifier.fillMaxWidth(),
                    showClearButton = false,
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_username_label))
                    },
                    supportingText = {
                        UiText(stringResource(R.string.sample_text_fields_username_supporting))
                    }
                )

                UiOutlinedTextField(
                    state = verifiedState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(
                            stringResource(R.string.sample_text_fields_verified_label)
                        )
                    },
                    placeholder = {
                        UiText(
                            stringResource(R.string.sample_text_fields_verified_placeholder)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    showClearButton = true
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_affixes_title),
                description = stringResource(R.string.sample_text_fields_affixes_description)
            ) {
                UiOutlinedTextField(
                    state = priceState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_price_label))
                    },
                    prefix = {
                        UiText(stringResource(R.string.sample_text_fields_currency_prefix))
                    },
                    keyboardOptions = UiTextFieldDefaults.keyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    showClearButton = false
                )

                UiTextField(
                    state = weightState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(stringResource(R.string.sample_text_fields_weight_label))
                    },
                    suffix = {
                        UiText(stringResource(R.string.sample_text_fields_weight_suffix))
                    },
                    keyboardOptions = UiTextFieldDefaults.keyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    showClearButton = false
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_transformations_title),
                description = stringResource(R.string.sample_text_fields_transformations_description)
            ) {
                UiOutlinedTextField(
                    state = numericState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(
                            stringResource(
                                R.string.sample_text_fields_digits_only_label
                            )
                        )
                    },
                    supportingText = {
                        UiText(
                            stringResource(
                                R.string.sample_text_fields_digits_only_supporting
                            )
                        )
                    },
                    keyboardOptions = UiTextFieldDefaults.keyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    inputTransformation = UiTextFieldTransformations.digitsOnly(),
                    maxLength = 8,
                    showCharacterCount = true,
                    showClearButton = false
                )

                UiTextField(
                    state = formattedState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(
                            stringResource(
                                R.string.sample_text_fields_formatted_label
                            )
                        )
                    },
                    supportingText = {
                        UiText(
                            stringResource(
                                R.string.sample_text_fields_formatted_supporting
                            )
                        )
                    },
                    keyboardOptions = UiTextFieldDefaults.keyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    inputTransformation =  UiTextFieldTransformations.digitsOnly(),
                    outputTransformation = UiTextFieldTransformations.groupedCharacters(),
                    maxLength = 8,
                    showCharacterCount = true,
                    showClearButton = false
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_text_fields_customization_title),
                description = stringResource(R.string.sample_text_fields_customization_description)
            ) {
                UiOutlinedTextField(
                    state = customOutlinedState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(
                            stringResource(R.string.sample_text_fields_custom_outlined_label)
                        )
                    },
                    placeholder = {
                        UiText(
                            stringResource(R.string.sample_text_fields_custom_placeholder)
                        )
                    },
                    shape = UiShapesCut.extraSmall,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SampleCustomizationAccentColor,
                        unfocusedBorderColor = SampleCustomizationAccentColor,
                        focusedLabelColor = SampleCustomizationAccentColor,
                        unfocusedLabelColor = SampleCustomizationAccentColor,
                        focusedTextColor = SampleCustomizationAccentColor,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    ),
                    focusedBorderThickness = UiStrokeThick,
                    unfocusedBorderThickness = UiStrokeMedium,
                    showClearButton = false
                )

                UiTextField(
                    state = customFilledState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(
                            stringResource(R.string.sample_text_fields_custom_filled_label)
                        )
                    },
                    placeholder = {
                        UiText(
                            stringResource(R.string.sample_text_fields_custom_placeholder)
                        )
                    },
                    shape = UiShapes.medium,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = SampleCustomizationAccentColor,
                        unfocusedIndicatorColor = SampleCustomizationAccentColor,
                        focusedLabelColor = SampleCustomizationAccentColor,
                        unfocusedLabelColor = SampleCustomizationAccentColor,
                        focusedTextColor = SampleCustomizationAccentColor,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    ),
                    focusedIndicatorThickness = UiStrokeThick,
                    unfocusedIndicatorThickness = UiStrokeMedium,
                    showClearButton = false
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
                    state = validationState,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        UiText(
                            stringResource(R.string.sample_text_fields_access_code_label)
                        )
                    },
                    placeholder = {
                        UiText(
                            stringResource(R.string.sample_text_fields_access_code_placeholder)
                        )
                    },
                    supportingText = {
                        if (validationRequested && validationError == null) {
                            UiText(
                                text = stringResource(
                                    R.string.sample_text_fields_validation_success
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            UiText(
                                text = stringResource(
                                    R.string.sample_text_fields_access_code_supporting
                                )
                            )
                        }
                    },
                    keyboardOptions = UiTextFieldDefaults.keyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    inputTransformation = UiTextFieldTransformations.digitsOnly(),
                    maxLength = 6,
                    isError = validationError != null,
                    errorText = validationError?.let { stringResource(it) },
                    showClearButton = true
                )

                UiButton(
                    onClick = {
                        validationRequested = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UiText(
                        stringResource(R.string.sample_text_fields_validate_action)
                    )
                }
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
                UiTextFieldPasswordDemo()
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