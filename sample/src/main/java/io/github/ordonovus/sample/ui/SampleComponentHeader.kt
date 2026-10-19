package io.github.ordonovus.sample.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import io.github.ordonovus.sample.R
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiStrokeThin

/**
 * Displays a responsive header for a component demonstration.
 *
 * The header provides a shared layout while allowing each component
 * to supply its own contextual preview.
 *
 * @param title Display name of the component.
 * @param description Short description of the component.
 * @param modifier Modifier applied to the header.
 * @param demo Optional composable showing a component use case.
 */
@Composable
fun SampleComponentHeader(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    demo: @Composable () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme
    val isDarkTheme = colorScheme.background.luminance() < 0.5f

    val backgroundColors = if (isDarkTheme) {
        listOf(
            Color(0xFF102A52),
            Color(0xFF071A37)
        )
    } else {
        listOf(
            Color(0xFFEAF3FF),
            Color(0xFFDCEBFF)
        )
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = Color.Transparent,
        border = BorderStroke(
            width = UiStrokeThin,
            color = colorScheme.outlineVariant.copy(alpha = 0.5f)
        )
    ) {
        BoxWithConstraints(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(backgroundColors)
            )
        ) {
            val compactLayout =
                maxWidth < SampleShowcaseCompactBreakpoint

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        if (compactLayout) {
                            UiDimensMedium
                        } else {
                            UiDimensLarge
                        }
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    UiDimensLarge
                )
            ) {
                if (compactLayout) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
                    ) {
                        SampleHeaderInformation(
                            title = title,
                            description = description
                        )

                        demo()
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(UiDimensLarge),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            SampleHeaderInformation(
                                title = title,
                                description = description
                            )
                        }

                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            demo()
                        }
                    }
                }
            }
        }
    }
}

/**
 * Displays the shared title and description of a component.
 *
 * @param title Component title.
 * @param description Component description.
 */
@Composable
private fun SampleHeaderInformation(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            Text(
                text = stringResource(R.string.component_header_material3),
                modifier = Modifier.padding(
                    horizontal = UiDimensMedium,
                    vertical = UiDimensSmall
                ),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}