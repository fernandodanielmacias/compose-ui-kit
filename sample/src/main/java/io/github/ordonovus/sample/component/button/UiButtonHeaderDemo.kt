package io.github.ordonovus.sample.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import io.github.ordonovus.component.button.UiButton
import io.github.ordonovus.component.button.UiFilledTonalButton
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.textfield.UiOutlinedTextField
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.feedback.LocalSampleSnackbarHostState
import io.github.ordonovus.sample.ui.layout.SampleButtonHeaderNameMaxLength
import io.github.ordonovus.sample.ui.layout.SampleDemoContainer
import io.github.ordonovus.sample.ui.feedback.showSampleMessage
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiTypographyTitleMedium

/**
 * Displays an interactive demonstration of button usage.
 *
 * The demonstration uses Compose UI Kit components to edit,
 * save, and restore a sample profile name.
 *
 * Feedback messages are displayed through the shared snackbar host.
 *
 * @param modifier Modifier applied to the demonstration container.
 */
@Composable
fun UiButtonHeaderDemo(
    modifier: Modifier = Modifier
) {
    val nameState = rememberTextFieldState()
    var savedName by rememberSaveable { mutableStateOf("") }

    val currentName = nameState.text.toString()
    val hasChanges = currentName != savedName
    val canSave = hasChanges && currentName.isNotBlank()

    val snackbarHostState = LocalSampleSnackbarHostState.current
    val coroutineScope = rememberCoroutineScope()

    val profileTitle = stringResource(
        R.string.button_header_profile_title
    )
    val nameLabel = stringResource(
        R.string.button_header_name_label
    )
    val namePlaceholder = stringResource(
        R.string.button_header_name_placeholder
    )
    val cancelLabel = stringResource(
        R.string.button_header_cancel
    )
    val saveLabel = stringResource(
        R.string.button_header_save
    )
    val savedMessage = stringResource(
        R.string.button_header_saved_message
    )
    val canceledMessage = stringResource(
        R.string.button_header_canceled_message
    )

    SampleDemoContainer {
        Column(
            modifier = Modifier.padding(UiDimensMedium),
            verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
        ) {
            UiText(
                text = profileTitle,
                style = UiTypographyTitleMedium,
                fontWeight = FontWeight.SemiBold
            )

            UiOutlinedTextField(
                state = nameState,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    UiText(text = nameLabel)
                },
                placeholder = {
                    UiText(text = namePlaceholder)
                },
                maxLength = SampleButtonHeaderNameMaxLength,
                showClearButton = true
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    UiDimensSmall,
                    Alignment.End
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UiFilledTonalButton(
                    onClick = {
                        if (hasChanges) {
                            nameState.edit {
                                replace(
                                    start = 0,
                                    end = length,
                                    text = savedName
                                )
                            }

                            snackbarHostState.showSampleMessage(
                                scope = coroutineScope,
                                message = canceledMessage
                            )
                        }
                    },
                    enabled = hasChanges
                ) {
                    UiText(text = cancelLabel)
                }

                UiButton(
                    onClick = {
                        if (canSave) {
                            savedName = currentName

                            snackbarHostState.showSampleMessage(
                                scope = coroutineScope,
                                message = savedMessage
                            )
                        }
                    },
                    enabled = canSave
                ) {
                    UiText(text = saveLabel)
                }
            }
        }
    }
}