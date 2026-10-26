
package io.github.ordonovus.sample.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.button.UiButton
import io.github.ordonovus.component.button.UiButtonDefaults
import io.github.ordonovus.component.button.UiElevatedButton
import io.github.ordonovus.component.button.UiFilledTonalButton
import io.github.ordonovus.component.button.UiOutlinedButton
import io.github.ordonovus.component.button.UiTextButton
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.feedback.LocalSampleSnackbarHostState
import io.github.ordonovus.sample.ui.layout.SampleComponentHeader
import io.github.ordonovus.sample.ui.preview.SamplePreview
import io.github.ordonovus.sample.ui.layout.SampleSection
import io.github.ordonovus.sample.ui.feedback.showSampleMessage
import io.github.ordonovus.sample.ui.preview.SamplePreviewDevice
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiShapeNone
import io.github.ordonovus.theme.UiShapes
import io.github.ordonovus.theme.UiShapesCut
import io.github.ordonovus.theme.UiStrokeThin

/**
 * Displays the button component showcase used by the sample module.
 *
 * Examples are organized by variant, state, layout behavior, and
 * customization. Enabled buttons provide contextual snackbar feedback.
 */
@Composable
fun UiButtonSample() {
    val snackbarHostState = LocalSampleSnackbarHostState.current
    val coroutineScope = rememberCoroutineScope()

    val filledMessage = stringResource(R.string.sample_button_filled_clicked)
    val elevatedMessage = stringResource(R.string.sample_button_elevated_clicked)
    val outlinedMessage = stringResource(R.string.sample_button_outlined_clicked)
    val tonalMessage = stringResource(R.string.sample_button_tonal_clicked)
    val textMessage = stringResource(R.string.sample_button_text_clicked)
    val saveMessage = stringResource(R.string.sample_button_save_clicked)
    val fullWidthMessage = stringResource(R.string.sample_button_full_width_clicked)
    val roundedMessage = stringResource(R.string.sample_button_rounded_clicked)
    val cutMessage = stringResource(R.string.sample_button_cut_clicked)
    val squareMessage = stringResource(R.string.sample_button_square_clicked)
    val borderMessage = stringResource(R.string.sample_button_border_clicked)
    val destructiveMessage = stringResource(R.string.sample_button_destructive_clicked)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(UiDimensMedium),
        verticalArrangement = Arrangement.spacedBy(UiDimensLarge)
    ) {
        item {
            SampleComponentHeader(
                title = stringResource(R.string.button_title),
                description = stringResource(R.string.button_description),
                demo = {
                    UiButtonHeaderDemo()
                }
            )
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_buttons_variants_title
                ),
                description = stringResource(
                    R.string.sample_buttons_variants_description
                )
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(UiDimensSmall),
                    verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
                ) {
                    UiButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, filledMessage)
                        }
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_filled)
                        )
                    }

                    UiElevatedButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, elevatedMessage)
                        }
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_elevated)
                        )
                    }

                    UiOutlinedButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, outlinedMessage)
                        }
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_outlined)
                        )
                    }

                    UiFilledTonalButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, tonalMessage)
                        }
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_filled_tonal)
                        )
                    }

                    UiTextButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, textMessage)
                        }
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_text)
                        )
                    }
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_buttons_states_title
                ),
                description = stringResource(
                    R.string.sample_buttons_states_description
                )
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(UiDimensSmall),
                    verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
                ) {
                    UiButton(
                        onClick = {},
                        enabled = false
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_filled)
                        )
                    }

                    UiFilledTonalButton(
                        onClick = {},
                        enabled = false
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_filled_tonal)
                        )
                    }

                    UiElevatedButton(
                        onClick = {},
                        enabled = false
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_elevated)
                        )
                    }

                    UiOutlinedButton(
                        onClick = {},
                        enabled = false
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_outlined)
                        )
                    }

                    UiTextButton(
                        onClick = {},
                        enabled = false
                    ) {
                        UiText(
                            stringResource(R.string.sample_button_text)
                        )
                    }
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_buttons_icons_title
                ),
                description = stringResource(
                    R.string.sample_buttons_icons_description
                )
            ) {
                UiButton(
                    onClick = {
                        snackbarHostState.showSampleMessage(coroutineScope, saveMessage)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null,
                        modifier = Modifier.size(UiButtonDefaults.IconSize)
                    )

                    Spacer(
                        modifier = Modifier.width(UiButtonDefaults.IconSpacing)
                    )

                    UiText(
                        text = stringResource(R.string.sample_button_save)
                    )
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_buttons_layout_title
                ),
                description = stringResource(
                    R.string.sample_buttons_layout_description
                )
            ) {
                UiButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        snackbarHostState.showSampleMessage(coroutineScope, fullWidthMessage)
                    }
                ) {
                    UiText(
                        stringResource(R.string.sample_button_full_width)
                    )
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_buttons_customization_title
                ),
                description = stringResource(
                    R.string.sample_buttons_customization_description
                )
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(UiDimensSmall),
                    verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
                ) {
                    UiButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, roundedMessage)
                        },
                        shape = UiShapes.medium
                    ) {
                        UiText(
                            text = stringResource(R.string.sample_button_rounded)
                        )
                    }

                    UiButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, cutMessage)
                        },
                        shape = UiShapesCut.extraSmall
                    ) {
                        UiText(
                            text = stringResource(R.string.sample_button_cut)
                        )
                    }

                    UiButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, squareMessage)
                        },
                        shape = UiShapeNone
                    ) {
                        UiText(
                            text = stringResource(R.string.sample_button_square)
                        )
                    }

                    UiOutlinedButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, borderMessage)
                        },
                        border = BorderStroke(
                            width = UiStrokeThin,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        UiText(
                            text = stringResource(
                                R.string.sample_button_custom_border
                            )
                        )
                    }
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_button_destructive_title
                ),
                description = stringResource(
                    R.string.sample_button_destructive_description
                )
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(UiDimensSmall),
                    verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
                ) {
                    UiButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, destructiveMessage)
                        },
                        colors = UiButtonDefaults.destructiveColors()
                    ) {
                        UiText(
                            text = stringResource(R.string.sample_button_delete)
                        )
                    }

                    UiFilledTonalButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(coroutineScope, destructiveMessage)
                        },
                        colors = UiButtonDefaults.destructiveTonalColors()
                    ) {
                        UiText(
                            text = stringResource(
                                R.string.sample_button_delete_tonal
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * Previews the button showcase using the light theme.
 */
@Preview(
    name = "Buttons - Light",
    showBackground = true,
    showSystemUi = true,
    device = SamplePreviewDevice
)
@Composable
private fun UiButtonSampleLightPreview() {
    SamplePreview {
        UiButtonSample()
    }
}

/**
 * Previews the button showcase using the dark theme.
 */
@Preview(
    name = "Buttons - Dark",
    showBackground = true,
    showSystemUi = true,
    device = SamplePreviewDevice
)
@Composable
private fun UiButtonSampleDarkPreview() {
    SamplePreview(
        darkTheme = true
    ) {
        UiButtonSample()
    }
}
