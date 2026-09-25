package io.github.ordonovus.sample.catalog

/**
 * Represents the component samples available in the Compose UI Kit
 * sample application.
 */
enum class SampleComponent {
    Button,
    Typography,
    Text,
    TextField
}

/**
 * Sample displayed when the sample application starts.
 *
 * Change this value to select the component to test.
 */
val DefaultSampleComponent = SampleComponent.TextField