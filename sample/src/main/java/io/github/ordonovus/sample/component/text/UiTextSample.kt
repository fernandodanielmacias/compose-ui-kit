package io.github.ordonovus.sample.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.text.UiTextVariant
import io.github.ordonovus.sample.ui.SamplePreview
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiKitTheme

/**
 * Displays the semantic text variants provided by Compose UI Kit.
 *
 * This sample demonstrates the default typography hierarchy, semantic error
 * coloring, and annotated text support exposed by [UiText].
 */
@Composable
fun UiTextSample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiDimensMedium),
        verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
    ) {
        UiText(
            text = "Display",
            variant = UiTextVariant.Display
        )

        UiText(
            text = "Heading",
            variant = UiTextVariant.Heading
        )

        UiText(
            text = "Title",
            variant = UiTextVariant.Title
        )

        UiText(
            text = "Body",
            variant = UiTextVariant.Body
        )

        UiText(
            text = "Secondary body",
            variant = UiTextVariant.BodySecondary
        )

        UiText(
            text = "Label",
            variant = UiTextVariant.Label
        )

        UiText(
            text = "This is an error message",
            variant = UiTextVariant.BodySecondary,
            color = MaterialTheme.colorScheme.error
        )

        UiText(
            text = buildAnnotatedString {
                append("Status: ")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Approved")
                }
            },
            variant = UiTextVariant.Body
        )
    }
}

/**
 * Previews [UiTextSample] using the light theme.
 */
@Preview(
    name = "Text - Light",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTextSampleLightPreview() {
    SamplePreview {
        UiTextSample()
    }
}

/**
 * Previews [UiTextSample] using the dark theme.
 */
@Preview(
    name = "Text - Dark",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun UiTextSampleDarkPreview() {
    SamplePreview(
        darkTheme = true
    ) {
        UiTextSample()
    }
}