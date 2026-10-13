package io.github.ordonovus.sample.catalog

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.SmartButton
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Title
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.ordonovus.sample.R

/**
 * Represents the component samples available in the Compose UI Kit
 * sample application.
 *
 * @property titleRes Resource containing the localized component name.
 * @property icon Icon representing the component in the navigation menu.
 */
enum class SampleComponent(
    @param:StringRes val titleRes: Int,
    val icon: ImageVector
) {
    Button(
        titleRes = R.string.sample_navigation_button,
        icon = Icons.Default.SmartButton
    ),

    Typography(
        titleRes = R.string.sample_navigation_typography,
        icon = Icons.Default.Title
    ),

    Text(
        titleRes = R.string.sample_navigation_text,
        icon = Icons.AutoMirrored.Filled.Notes
    ),

    TextField(
        titleRes = R.string.sample_navigation_text_field,
        icon = Icons.Default.TextFields
    )
}

/**
 * Sample displayed when the sample application starts.
 */
val DefaultSampleComponent = SampleComponent.Button