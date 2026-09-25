package io.github.ordonovus.sample.catalog

import androidx.compose.runtime.Composable
import io.github.ordonovus.sample.component.button.UiButtonSample
import io.github.ordonovus.sample.component.text.UiTextSample
import io.github.ordonovus.sample.component.text.UiTypographySample
import io.github.ordonovus.sample.component.textfield.UiTextFieldSample

/**
 * Displays the sample associated with the specified component.
 *
 * This composable centralizes the mapping between [SampleComponent] values
 * and their corresponding sample content.
 *
 * @param component Component sample to display.
 */
@Composable
fun SampleContent(
    component: SampleComponent
) {
    when (component) {
        SampleComponent.Button -> UiButtonSample()
        SampleComponent.Typography -> UiTypographySample()
        SampleComponent.Text -> UiTextSample()
        SampleComponent.TextField -> UiTextFieldSample()
    }
}