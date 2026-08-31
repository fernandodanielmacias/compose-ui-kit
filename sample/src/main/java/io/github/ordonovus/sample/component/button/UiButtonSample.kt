package io.github.ordonovus.sample.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.button.UiButton
import io.github.ordonovus.component.button.UiElevatedButton
import io.github.ordonovus.component.button.UiFilledTonalButton
import io.github.ordonovus.component.button.UiOutlinedButton
import io.github.ordonovus.component.button.UiTextButton
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiKitTheme

/**
 * Displays the complete button component showcase used by the sample module.
 *
 * This composable is intended only for demonstrating the public button APIs
 * provided by Compose UI Kit.
 */
@Composable
fun UiButtonSample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiDimensMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
    ) {
        UiButton(
            onClick = {}
        ) {
            Text("Filled Button")
        }

        UiFilledTonalButton(
            onClick = {}
        ) {
            Text("Filled Tonal Button")
        }

        UiElevatedButton(
            onClick = {}
        ) {
            Text("Elevated Button")
        }

        UiOutlinedButton(
            onClick = {}
        ) {
            Text("Outlined Button")
        }

        UiTextButton(
            onClick = {}
        ) {
            Text("Text Button")
        }

        UiButton(
            onClick = {},
            enabled = false
        ) {
            Text("Disabled Button")
        }

        UiButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = null
            )

            Text("Save")
        }

        UiButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Text("Full Width")
        }
    }
}

/**
 * Previews all button variants using the light theme.
 */
@Preview(
    name = "Buttons - Light",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun UiButtonSampleLightPreview() {
    UiKitTheme(
        darkTheme = false
    ) {
        UiButtonSample()
    }
}

/**
 * Previews all button variants using the dark theme.
 */
@Preview(
    name = "Buttons - Dark",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun UiButtonSampleDarkPreview() {
    UiKitTheme(
        darkTheme = true
    ) {
        UiButtonSample()
    }
}