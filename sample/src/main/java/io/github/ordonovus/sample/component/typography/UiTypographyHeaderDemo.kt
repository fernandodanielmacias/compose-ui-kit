package io.github.ordonovus.sample.component.typography

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.text.UiTextVariant
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.layout.SampleDemoContainer
import io.github.ordonovus.theme.UiDimensSmall

/**
 * Displays a compact typography demonstration in the component header.
 *
 * Combines semantic heading and supporting text styles to demonstrate
 * a consistent visual hierarchy using the active Material theme.
 */
@Composable
fun UiTypographyHeaderDemo() {
    SampleDemoContainer {
        Column(
            verticalArrangement = Arrangement.spacedBy(UiDimensSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UiText(
                text = stringResource(
                    R.string.sample_typography_header_demo
                ),
                variant = UiTextVariant.Heading,
                textAlign = TextAlign.Center
            )

            UiText(
                text = stringResource(
                    R.string.sample_typography_header_supporting
                ),
                variant = UiTextVariant.BodySecondary,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}