package io.github.ordonovus.sample.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ordonovus.sample.catalog.DefaultSampleComponent
import io.github.ordonovus.sample.catalog.SampleComponent
import io.github.ordonovus.sample.catalog.SampleContent

/**
 * Root screen of the Compose UI Kit sample application.
 *
 * This screen provides the common layout used by component samples and
 * delegates the selected sample content to [SampleContent].
 *
 * @param selectedSample Component sample to display.
 */
@Composable
fun MainScreen(
    selectedSample: SampleComponent = DefaultSampleComponent
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            SampleContent(
                component = selectedSample
            )
        }
    }
}