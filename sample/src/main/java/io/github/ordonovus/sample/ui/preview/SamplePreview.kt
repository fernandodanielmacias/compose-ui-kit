package io.github.ordonovus.sample.ui.preview

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ordonovus.theme.UiKitTheme

/**
 * Provides the common environment used by Compose UI Kit component previews.
 *
 * Applies the Compose UI Kit theme and a Material 3 scaffold that handles
 * system window insets, ensuring preview content is displayed correctly
 * below the status bar and above the navigation bar.
 *
 * The preview environment also provides a themed surface so component
 * samples are rendered consistently in both light and dark modes.
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
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                content()
            }
        }
    }
}