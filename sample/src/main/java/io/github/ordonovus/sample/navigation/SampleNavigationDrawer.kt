package io.github.ordonovus.sample.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.catalog.SampleComponent
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiDimensXLarge

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
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = UiDimensLarge,
                    vertical = UiDimensXLarge
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(UiDimensMedium)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_ui_kit),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
            ) {
                Text(
                    text = stringResource(R.string.sample_catalog_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = stringResource(R.string.sample_catalog_description),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        HorizontalDivider()

        Text(
            text = stringResource(R.string.sample_navigation_components),
            modifier = Modifier.padding(
                start = UiDimensXLarge,
                top = UiDimensLarge,
                bottom = UiDimensSmall
            ),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SampleComponent.entries.forEach { component ->
            val isSelected = component == selectedSample

            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(component.titleRes))
                },
                selected = isSelected,
                onClick = { onSampleSelected(component) },
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
                    horizontal = UiDimensSmall,
                    vertical = UiDimensSmall
                ),
                shape = MaterialTheme.shapes.medium,
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }
    }
}