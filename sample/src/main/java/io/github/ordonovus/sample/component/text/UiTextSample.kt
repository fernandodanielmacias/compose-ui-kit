package io.github.ordonovus.sample.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.text.UiTextVariant
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.layout.SampleComponentHeader
import io.github.ordonovus.sample.ui.layout.SampleScrollToTopContainer
import io.github.ordonovus.sample.ui.layout.SampleSection
import io.github.ordonovus.sample.ui.preview.SamplePreview
import io.github.ordonovus.sample.ui.preview.SamplePreviewDevice
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium

/**
 * Displays the semantic text variants provided by Compose UI Kit.
 *
 * Demonstrates UiText with semantic typography variants, Material 3
 * color overrides, and annotated text styling.
 *
 * A practical example is displayed in the component header, while
 * detailed examples are organized into showcase sections.
 */
@Composable
fun UiTextSample() {
    val listState = rememberLazyListState()

    SampleScrollToTopContainer(
        listState = listState
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(UiDimensMedium),
            verticalArrangement = Arrangement.spacedBy(UiDimensLarge)
        ) {
            item {
                SampleComponentHeader(
                    title = stringResource(R.string.sample_text_title),
                    description = stringResource(R.string.sample_text_description),
                    demo = {
                        UiTextHeaderDemo()
                    }
                )
            }

            item {
                SampleSection(
                    title = stringResource(R.string.sample_text_typography_title),
                    description = stringResource(
                        R.string.sample_text_typography_description
                    )
                ) {
                    UiText(
                        text = stringResource(R.string.sample_text_display),
                        variant = UiTextVariant.Display
                    )

                    UiText(
                        text = stringResource(R.string.sample_text_heading),
                        variant = UiTextVariant.Heading
                    )

                    UiText(
                        text = stringResource(R.string.sample_text_variant_title),
                        variant = UiTextVariant.Title
                    )

                    UiText(
                        text = stringResource(R.string.sample_text_body),
                        variant = UiTextVariant.Body
                    )

                    UiText(
                        text = stringResource(
                            R.string.sample_text_body_secondary
                        ),
                        variant = UiTextVariant.BodySecondary
                    )

                    UiText(
                        text = stringResource(R.string.sample_text_label),
                        variant = UiTextVariant.Label
                    )
                }
            }

            item {
                SampleSection(
                    title = stringResource(R.string.sample_text_colors_title),
                    description = stringResource(
                        R.string.sample_text_colors_description
                    )
                ) {
                    UiText(
                        text = stringResource(R.string.sample_text_primary_color),
                        variant = UiTextVariant.Body,
                        color = MaterialTheme.colorScheme.primary
                    )

                    UiText(
                        text = stringResource(R.string.sample_text_secondary_color),
                        variant = UiTextVariant.Body,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    UiText(
                        text = stringResource(R.string.sample_text_error),
                        variant = UiTextVariant.BodySecondary,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            item {
                val statusPrefix = stringResource(
                    R.string.sample_text_status_prefix
                )
                val approvedText = stringResource(
                    R.string.sample_text_status_approved
                )

                SampleSection(
                    title = stringResource(R.string.sample_text_annotated_title),
                    description = stringResource(
                        R.string.sample_text_annotated_description
                    )
                ) {
                    UiText(
                        text = buildAnnotatedString {
                            append(statusPrefix)

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(approvedText)
                            }
                        },
                        variant = UiTextVariant.Body
                    )
                }
            }
        }
    }
}

/**
 * Previews [UiTextSample] using the light theme.
 */
@Preview(
    name = "Text - Light",
    showBackground = true,
    showSystemUi = true,
    device = SamplePreviewDevice
)
@Composable
private fun UiTextSampleLightPreview() {
    SamplePreview {
        UiTextSample()
    }
}

/**
 * Previews [UiTextSample] using the dark theme.
 */
@Preview(
    name = "Text - Dark",
    showBackground = true,
    showSystemUi = true,
    device = SamplePreviewDevice
)
@Composable
private fun UiTextSampleDarkPreview() {
    SamplePreview(
        darkTheme = true
    ) {
        UiTextSample()
    }
}