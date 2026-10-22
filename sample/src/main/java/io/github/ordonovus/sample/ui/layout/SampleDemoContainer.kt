package io.github.ordonovus.sample.ui.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import io.github.ordonovus.sample.ui.theme.SampleDemoBorderColor
import io.github.ordonovus.sample.ui.theme.SampleDemoContainerColor
import io.github.ordonovus.sample.ui.theme.SampleDemoShadowColor
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiShapes
import io.github.ordonovus.theme.UiStrokeThin

/**
 * Provides a consistent visual container for component header demos.
 *
 * Uses theme color roles to support light and dark appearances.
 */
@Composable
fun SampleDemoContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.shadow(
            elevation =SampleDemoContainerElevation,
            shape = UiShapes.large,
            clip = false,
            ambientColor = SampleDemoShadowColor,
            spotColor = SampleDemoShadowColor
        ),
        shape = UiShapes.large,
        shadowElevation = 0.dp,
        color = SampleDemoContainerColor,
        contentColor = MaterialTheme.colorScheme.onSurface,
        border = BorderStroke(
            width = UiStrokeThin,
            color = SampleDemoBorderColor
        )
    ) {
        Box(
            modifier = Modifier.padding(UiDimensMedium)
        ) {
            content()
        }
    }
}