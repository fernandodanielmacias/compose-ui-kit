package io.github.ordonovus.sample.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Background color used by interactive component demo containers.
 */
val SampleDemoContainerColor: Color
    @Composable get() = MaterialTheme.colorScheme.surfaceContainerLow

/**
 * Border color used by interactive component demo containers.
 */
val SampleDemoBorderColor: Color
    @Composable get() = MaterialTheme.colorScheme.primary

/**
 * Shadow color used to emphasize interactive sample containers.
 */
val SampleDemoShadowColor: Color
    @Composable get() = MaterialTheme.colorScheme.primary

/**
 * Accent color used to demonstrate custom component colors.
 */
val SampleCustomizationAccentColor = Color(0xFF008577)
