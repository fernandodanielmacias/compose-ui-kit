package io.github.ordonovus.sample.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.button.UiButton
import io.github.ordonovus.component.button.UiElevatedButton
import io.github.ordonovus.component.button.UiFilledTonalButton
import io.github.ordonovus.component.button.UiOutlinedButton
import io.github.ordonovus.component.button.UiTextButton
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.SampleComponentHeader
import io.github.ordonovus.sample.ui.SamplePreview
import io.github.ordonovus.sample.ui.SampleSection
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall

/**
 * Displays the button component showcase used by the sample module.
 *
 * Examples are organized by variant, state, and layout behavior.
 * Button variants use an adaptive flow layout.
 */
@Composable
fun UiButtonSample() {
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
                    horizontalArrangement = Arrangement.spacedBy(
                        UiDimensSmall
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        UiDimensSmall
                    )
                ) {
                    UiButton(onClick = {}) {
                        Text(
                            stringResource(R.string.sample_button_filled)
                        )
                    }

                    UiElevatedButton(onClick = {}) {
                        Text(
                            stringResource(R.string.sample_button_elevated)
                        )
                    }

                    UiOutlinedButton(onClick = {}) {
                        Text(
                            stringResource(R.string.sample_button_outlined)
                        )
                    }

                    UiFilledTonalButton(onClick = {}) {
                        Text(
                            stringResource(R.string.sample_button_filled_tonal)
                        )
                    }

                    UiTextButton(onClick = {}) {
                        Text(
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
                UiButton(
                    onClick = {},
                    enabled = false
                ) {
                    Text(
                        stringResource(R.string.sample_button_disabled)
                    )
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
                UiButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null
                    )

                    Text(
                        stringResource(R.string.sample_button_save)
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
                    onClick = {}
                ) {
                    Text(
                        stringResource(R.string.sample_button_full_width)
                    )
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
    widthDp = 360,
    heightDp = 700
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
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiButtonSampleDarkPreview() {
    SamplePreview(
        darkTheme = true
    ) {
        UiButtonSample()
    }
}