package io.github.ordonovus.sample.catalog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.SmartButton
import androidx.compose.material.icons.filled.Title
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.ordonovus.sample.R
import io.github.ordonovus.R as UiKitR

/**
 * Represents the component samples available in the Compose UI Kit
 * sample application.
 *
 * @property titleRes Resource containing the localized component name.
 * @property icon Optional Material icon representing the component.
 * @property iconRes Optional drawable resource used for custom icons.
 */
enum class SampleComponent(
    @param:StringRes val titleRes: Int,
    val icon: ImageVector? = null,
    @param:DrawableRes val iconRes: Int? = null
) {
    Button(
        titleRes = R.string.sample_navigation_button,
        icon = Icons.Default.SmartButton
    ),

    Fab(
        titleRes = R.string.sample_navigation_fab,
        icon = Icons.Default.AddCircle
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
        iconRes = UiKitR.drawable.ic_text_cursor_input
    )
}

/**
 * Sample displayed when the sample application starts.
 */
val DefaultSampleComponent = SampleComponent.Button