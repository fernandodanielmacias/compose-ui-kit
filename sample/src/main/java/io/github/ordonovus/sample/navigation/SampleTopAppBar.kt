package io.github.ordonovus.sample.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.catalog.SampleComponent
import io.github.ordonovus.sample.settings.SampleThemeMode
import io.github.ordonovus.sample.ui.SampleHeaderHeight
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiStrokeThin

/**
 * Displays the sample application's top app bar.
 *
 * @param selectedSample Currently selected component.
 * @param showMenuButton Whether the navigation menu button is visible.
 * @param selectedThemeMode Currently selected appearance mode.
 * @param onThemeModeSelected Callback invoked when the appearance changes.
 * @param onMenuClick Callback invoked when the navigation button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SampleTopAppBar(
    selectedSample: SampleComponent,
    showMenuButton: Boolean,
    isPermanentDrawer: Boolean = false,
    isDrawerExpanded: Boolean = false,
    selectedThemeMode: SampleThemeMode,
    onThemeModeSelected: (SampleThemeMode) -> Unit,
    onMenuClick: () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorScheme.surfaceContainerLow)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(selectedSample.titleRes),
                    style = MaterialTheme.typography.titleLarge,
                    color = colorScheme.onSurface
                )
            },
            navigationIcon = {
                if (showMenuButton) {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            painter = painterResource(
                                id = when {
                                    !isPermanentDrawer -> R.drawable.ic_panel_left_open
                                    isDrawerExpanded -> R.drawable.ic_panel_left_close
                                    else -> R.drawable.ic_panel_left_open
                                }
                            ),
                            contentDescription = stringResource(
                                when {
                                    !isPermanentDrawer -> R.string.sample_navigation_menu
                                    isDrawerExpanded -> R.string.sample_navigation_close
                                    else -> R.string.sample_navigation_open
                                }
                            ),
                            modifier = Modifier.size(UiDimensLarge),
                            tint = colorScheme.onSurface
                        )
                    }
                }
            },
            actions = {
                Row(
                    modifier = Modifier.padding(end = UiDimensMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(UiDimensSmall)
                ) {
                    SampleLanguageSelector()

                    SampleThemeSelector(
                        selectedMode = selectedThemeMode,
                        onModeSelected = onThemeModeSelected
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorScheme.surfaceContainerLow,
                titleContentColor = colorScheme.onSurface,
                navigationIconContentColor = colorScheme.onSurface,
                actionIconContentColor = colorScheme.onSurface
            ),
            expandedHeight = SampleHeaderHeight
        )

        HorizontalDivider(
            thickness = UiStrokeThin,
            color = colorScheme.outlineVariant.copy(alpha = 0.5f)
        )
    }
}
