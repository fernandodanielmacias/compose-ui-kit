package io.github.ordonovus.sample.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.sample.ui.SamplePreview
import io.github.ordonovus.theme.UiKitTheme
import io.github.ordonovus.theme.UiTypographyBody
import io.github.ordonovus.theme.UiTypographyBodySecondary
import io.github.ordonovus.theme.UiTypographyDisplay
import io.github.ordonovus.theme.UiTypographyHeading
import io.github.ordonovus.theme.UiTypographyLabel
import io.github.ordonovus.theme.UiTypographyTitle
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall

/**
 * Displays the semantic typography styles provided by Compose UI Kit.
 *
 * This composable is intended for visual verification of the typography
 * system in the sample module.
 */
@Composable
fun UiTypographySample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiDimensMedium),
        verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
    ) {
        Text(
            text = "Display",
            style = UiTypographyDisplay
        )

        Text(
            text = "Heading",
            style = UiTypographyHeading
        )

        Text(
            text = "Title",
            style = UiTypographyTitle
        )

        Text(
            text = "Body",
            style = UiTypographyBody
        )

        Text(
            text = "Secondary body",
            style = UiTypographyBodySecondary
        )

        Text(
            text = "Label",
            style = UiTypographyLabel
        )
    }
}

/**
 * Previews the UI Kit semantic typography using the light theme.
 */
@Preview(
    name = "Typography - Light",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTypographySampleLightPreview() {
    SamplePreview {
        UiTypographySample()
    }
}

/**
 * Previews the UI Kit semantic typography using the dark theme.
 */
@Preview(
    name = "Typography - Dark",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTypographySampleDarkPreview() {
    SamplePreview(
        darkTheme = true
    ) {
        UiTypographySample()
    }
}