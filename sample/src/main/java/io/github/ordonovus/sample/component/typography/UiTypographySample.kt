package io.github.ordonovus.sample.component.typography

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.text.UiTextVariant
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.layout.SampleComponentHeader
import io.github.ordonovus.sample.ui.layout.SampleSection
import io.github.ordonovus.sample.ui.preview.SamplePreview
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiKitTheme
import io.github.ordonovus.theme.UiTypographyBody
import io.github.ordonovus.theme.UiTypographyBodySecondary
import io.github.ordonovus.theme.UiTypographyDisplay
import io.github.ordonovus.theme.UiTypographyHeading
import io.github.ordonovus.theme.UiTypographyLabel
import io.github.ordonovus.theme.UiTypographyTitle

/**
 * Displays the semantic typography styles provided by Compose UI Kit.
 *
 * Demonstrates the default typography tokens applied directly to Material 3
 * Text components and the ability to customize typography through UiKitTheme.
 *
 * A practical example is displayed in the component header, while detailed
 * examples are organized into showcase sections.
 */
@Composable
fun UiTypographySample() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(UiDimensMedium),
        verticalArrangement = Arrangement.spacedBy(UiDimensLarge)
    ) {
        item {
            SampleComponentHeader(
                title = stringResource(R.string.sample_typography_title),
                description = stringResource(
                    R.string.sample_typography_description
                ),
                demo = {
                    UiTypographyHeaderDemo()
                }
            )
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_typography_styles_title
                ),
                description = stringResource(
                    R.string.sample_typography_styles_description
                )
            ) {
                Text(
                    text = stringResource(R.string.sample_typography_display),
                    style = UiTypographyDisplay
                )

                Text(
                    text = stringResource(R.string.sample_typography_heading),
                    style = UiTypographyHeading
                )

                Text(
                    text = stringResource(
                        R.string.sample_typography_variant_title
                    ),
                    style = UiTypographyTitle
                )

                Text(
                    text = stringResource(R.string.sample_typography_body),
                    style = UiTypographyBody
                )

                Text(
                    text = stringResource(
                        R.string.sample_typography_body_secondary
                    ),
                    style = UiTypographyBodySecondary
                )

                Text(
                    text = stringResource(R.string.sample_typography_label),
                    style = UiTypographyLabel
                )
            }
        }

        item {
            SampleSection(
                title = stringResource(
                    R.string.sample_typography_custom_title
                ),
                description = stringResource(
                    R.string.sample_typography_custom_description
                )
            ) {
                UiText(
                    text = stringResource(
                        R.string.sample_typography_default_example
                    ),
                    variant = UiTextVariant.Heading
                )

                val customTypography = MaterialTheme.typography.copy(
                    headlineLarge = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = FontFamily.Serif
                    )
                )

                UiKitTheme(
                    colorScheme = MaterialTheme.colorScheme,
                    typography = customTypography
                ) {
                    UiText(
                        text = stringResource(
                            R.string.sample_typography_custom_example
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        variant = UiTextVariant.Heading
                    )
                }
            }
        }
    }
}

/**
 * Previews [UiTypographySample] using the light theme.
 */
@Preview(
    name = "Typography - Light",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTypographySampleLightPreview() {
    SamplePreview {
        UiTypographySample()
    }
}

/**
 * Previews [UiTypographySample] using the dark theme.
 */
@Preview(
    name = "Typography - Dark",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTypographySampleDarkPreview() {
    SamplePreview(
        darkTheme = true
    ) {
        UiTypographySample()
    }
}