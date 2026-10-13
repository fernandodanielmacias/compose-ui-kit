package io.github.ordonovus.sample.component.button

import androidx.compose.foundation.layout.Arrangement
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

/**
 * Displays the button component showcase used by the sample module.
 *
 * Examples are organized by variant, state, and layout behavior.
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
                title = stringResource(R.string.sample_buttons_title),
                description = stringResource(R.string.sample_buttons_description)
            )
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_buttons_variants_title),
                description = stringResource(R.string.sample_buttons_variants_description)
            ) {
                UiButton(onClick = {}) {
                    Text(stringResource(R.string.sample_button_filled))
                }

                UiFilledTonalButton(onClick = {}) {
                    Text(stringResource(R.string.sample_button_filled_tonal))
                }

                UiElevatedButton(onClick = {}) {
                    Text(stringResource(R.string.sample_button_elevated))
                }

                UiOutlinedButton(onClick = {}) {
                    Text(stringResource(R.string.sample_button_outlined))
                }

                UiTextButton(onClick = {}) {
                    Text(stringResource(R.string.sample_button_text))
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_buttons_states_title),
                description = stringResource(R.string.sample_buttons_states_description)
            ) {
                UiButton(
                    onClick = {},
                    enabled = false
                ) {
                    Text(stringResource(R.string.sample_button_disabled))
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_buttons_icons_title),
                description = stringResource(R.string.sample_buttons_icons_description)
            ) {
                UiButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null
                    )

                    Text(stringResource(R.string.sample_button_save))
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_buttons_layout_title),
                description = stringResource(R.string.sample_buttons_layout_description)
            ) {
                UiButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(stringResource(R.string.sample_button_full_width))
                }
            }
        }

    }
}

/**
 * Previews all button variants using the light theme.
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
 * Previews all button variants using the dark theme.
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