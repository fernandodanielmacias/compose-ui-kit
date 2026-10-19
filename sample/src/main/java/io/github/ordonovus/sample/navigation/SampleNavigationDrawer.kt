package io.github.ordonovus.sample.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.catalog.SampleComponent
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiDimensXLarge
import io.github.ordonovus.theme.UiStrokeThin

/**
 * Displays the navigation destinations available in the sample catalog.
 *
 * @param selectedSample Currently selected component.
 * @param onSampleSelected Callback invoked when a component is selected.
 */
@Composable
internal fun SampleNavigationDrawer(
    selectedSample: SampleComponent,
    onSampleSelected: (SampleComponent) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorScheme.surfaceContainerLow)
    ) {
        SampleBrandHeader()

        HorizontalDivider(
            thickness = UiStrokeThin,
            color = colorScheme.outlineVariant.copy(alpha = 0.5f)
        )

        Text(
            text = stringResource(R.string.sample_navigation_components),
            modifier = Modifier.padding(
                start = UiDimensXLarge,
                top = UiDimensLarge,
                bottom = UiDimensSmall
            ),
            style = MaterialTheme.typography.labelMedium,
            color = colorScheme.primary
        )

        SampleComponent.entries.forEach { component ->
            val isSelected = component == selectedSample

            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(component.titleRes),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                selected = isSelected,
                onClick = {
                    onSampleSelected(component)
                },
                icon = {
                    Icon(
                        imageVector = component.icon,
                        contentDescription = null
                    )
                },
                badge = {
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(UiDimensMedium)
                        )
                    }
                },
                modifier = Modifier.padding(
                    horizontal = UiDimensMedium,
                    vertical = UiDimensSmall
                ),
                shape = MaterialTheme.shapes.large,
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = colorScheme.primaryContainer,
                    selectedTextColor = colorScheme.onPrimaryContainer,
                    selectedIconColor = colorScheme.primary,
                    selectedBadgeColor = colorScheme.primary,
                    unselectedContainerColor = colorScheme.surfaceContainerLow,
                    unselectedTextColor = colorScheme.onSurfaceVariant,
                    unselectedIconColor = colorScheme.onSurfaceVariant
                )
            )
        }
    }
}