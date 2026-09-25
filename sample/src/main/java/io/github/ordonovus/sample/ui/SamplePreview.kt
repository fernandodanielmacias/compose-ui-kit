package io.github.ordonovus.sample.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ordonovus.theme.UiKitTheme

/**
 * Provides the common environment used by Compose UI Kit component previews.
 *
 * This composable applies the Compose UI Kit theme and a surface that uses
 * the active background color, ensuring preview content is rendered
 * consistently in both light and dark themes.
 *
 * @param darkTheme Whether the preview should use the dark color scheme.
 * @param content Composable content displayed inside the preview environment.
 */
@Composable
fun SamplePreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    UiKitTheme(
        darkTheme = darkTheme
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}