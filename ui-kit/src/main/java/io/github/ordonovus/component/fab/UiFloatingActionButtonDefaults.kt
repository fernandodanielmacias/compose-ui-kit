package io.github.ordonovus.component.fab

import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

/**
 * Provides default values for the floating action buttons in Compose UI Kit.
 *
 * Defaults follow Material 3 and can be overridden by individual components.
 */
object UiFloatingActionButtonDefaults {

    /** Default shape for a standard floating action button. */
    val Shape: Shape
        @Composable get() = FloatingActionButtonDefaults.shape

    /** Default shape for a small floating action button. */
    val SmallShape: Shape
        @Composable get() = FloatingActionButtonDefaults.smallShape

    /** Default shape for a large floating action button. */
    val LargeShape: Shape
        @Composable get() = FloatingActionButtonDefaults.largeShape

    /** Default shape for an extended floating action button. */
    val ExtendedShape: Shape
        @Composable get() = FloatingActionButtonDefaults.extendedFabShape

    /** Recommended icon size for a large floating action button. */
    val LargeIconSize: Dp
        get() = FloatingActionButtonDefaults.LargeIconSize

    /** Default container color provided by Material 3. */
    val ContainerColor: Color
        @Composable get() = FloatingActionButtonDefaults.containerColor

    /**
     * Returns the default elevation configuration for floating action buttons.
     *
     * @return Elevation values for the different interaction states.
     */
    @Composable
    fun elevation(): FloatingActionButtonElevation {
        return FloatingActionButtonDefaults.elevation()
    }

    /**
     * Returns a lower-emphasis elevation configuration.
     *
     * @return Reduced elevation values for the different interaction states.
     */
    @Composable
    fun loweredElevation(): FloatingActionButtonElevation {
        return FloatingActionButtonDefaults.loweredElevation()
    }

    /**
     * Primary container color for a floating action button.
     *
     * Resolves dynamically from the active Material 3 color scheme.
     */
    val PrimaryContainerColor: Color
        @Composable get() = MaterialTheme.colorScheme.primaryContainer

    /**
     * Content color associated with [PrimaryContainerColor].
     */
    val PrimaryContentColor: Color
        @Composable get() = MaterialTheme.colorScheme.onPrimaryContainer

    /**
     * Secondary container color for a floating action button.
     *
     * Resolves dynamically from the active Material 3 color scheme.
     */
    val SecondaryContainerColor: Color
        @Composable get() = MaterialTheme.colorScheme.secondaryContainer

    /**
     * Content color associated with [SecondaryContainerColor].
     */
    val SecondaryContentColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSecondaryContainer

    /**
     * Tertiary container color for a floating action button.
     *
     * Resolves dynamically from the active Material 3 color scheme.
     */
    val TertiaryContainerColor: Color
        @Composable get() = MaterialTheme.colorScheme.tertiaryContainer

    /**
     * Content color associated with [TertiaryContainerColor].
     */
    val TertiaryContentColor: Color
        @Composable get() = MaterialTheme.colorScheme.onTertiaryContainer

}