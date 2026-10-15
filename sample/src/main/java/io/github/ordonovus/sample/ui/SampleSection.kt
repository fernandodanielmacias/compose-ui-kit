package io.github.ordonovus.sample.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensNone
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiStrokeThin

/**
 * Displays a reusable section for component demonstrations.
 *
 * Each section contains a title, an optional description, and a
 * themed surface for displaying interactive component examples.
 *
 * @param title Title of the demonstration section.
 * @param modifier Modifier applied to the section.
 * @param description Optional description of the demonstrated behavior.
 * @param content Composable examples displayed inside the section.
 */
@Composable
fun SampleSection(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = colorScheme.onSurface
        )

        if (!description.isNullOrBlank()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = colorScheme.onSurfaceVariant
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = UiDimensNone,
            border = BorderStroke(
                width = UiStrokeThin,
                color = colorScheme.outlineVariant.copy(alpha = 0.5f)
            )
        ) {
            Column(
                modifier = Modifier.padding(UiDimensMedium),
                verticalArrangement = Arrangement.spacedBy(UiDimensMedium),
                content = content
            )
        }
    }
}
