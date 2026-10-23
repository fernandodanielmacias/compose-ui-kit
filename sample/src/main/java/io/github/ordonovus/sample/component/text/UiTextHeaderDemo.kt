package io.github.ordonovus.sample.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.text.UiTextVariant
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.layout.SampleDemoContainer
import io.github.ordonovus.theme.UiDimensSmall

/**
 * Displays a compact text demonstration in the component header.
 *
 * Demonstrates semantic text styling and annotated content with a
 * theme-aware highlighted status.
 */
@Composable
fun UiTextHeaderDemo() {
    val statusLabel = stringResource(
        R.string.sample_text_header_status_label
    )
    val statusValue = stringResource(
        R.string.sample_text_header_status_value
    )
    val statusColor = MaterialTheme.colorScheme.primary

    SampleDemoContainer {
        Column(
            verticalArrangement = Arrangement.spacedBy(UiDimensSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UiText(
                text = stringResource(R.string.sample_text_header_demo),
                variant = UiTextVariant.Title,
                textAlign = TextAlign.Center
            )

            UiText(
                text = buildAnnotatedString {
                    append(statusLabel)
                    append(" ")

                    withStyle(
                        style = SpanStyle(
                            color = statusColor,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(statusValue)
                    }
                },
                variant = UiTextVariant.Body,
                textAlign = TextAlign.Center
            )
        }
    }
}