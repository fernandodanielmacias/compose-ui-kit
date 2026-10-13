package io.github.ordonovus.sample.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.catalog.DefaultSampleComponent
import io.github.ordonovus.sample.catalog.SampleComponent
import io.github.ordonovus.sample.catalog.SampleContent
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiDimensXLarge
import kotlinx.coroutines.launch

/**
 * Root screen of the Compose UI Kit sample application.
 *
 * Provides adaptive navigation for the available component samples.
 *
 * @param initialSample Component selected when the screen is first created.
 */
@Composable
fun MainScreen(
    initialSample: SampleComponent = DefaultSampleComponent
) {
    var selectedSample by rememberSaveable {
        mutableStateOf(initialSample)
    }

    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val windowWidth = with(density) {
        windowInfo.containerSize.width.toDp()
    }

    val usePermanentDrawer = windowWidth >= 840.dp

    if (usePermanentDrawer) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    modifier = Modifier.width(280.dp)
                ) {
                    SampleNavigationContent(
                        selectedSample = selectedSample,
                        onSampleSelected = { selectedSample = it }
                    )
                }
            }
        ) {
            SampleScreenContent(
                selectedSample = selectedSample,
                showMenuButton = false
            )
        }
    } else {
        val drawerState = rememberDrawerState(
            initialValue = DrawerValue.Closed
        )
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    SampleNavigationContent(
                        selectedSample = selectedSample,
                        onSampleSelected = { component ->
                            selectedSample = component
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        ) {
            SampleScreenContent(
                selectedSample = selectedSample,
                showMenuButton = true,
                onMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    }
}

/**
 * Displays the navigation destinations available in the sample catalog.
 *
 * @param selectedSample Currently selected component.
 * @param onSampleSelected Callback invoked when a component is selected.
 */
@Composable
private fun SampleNavigationContent(
    selectedSample: SampleComponent,
    onSampleSelected: (SampleComponent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = UiDimensLarge,
                vertical = UiDimensXLarge
            ),
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
                    Text(
                        text = stringResource(component.titleRes)
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

/**
 * Displays the selected sample within the common application layout.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SampleScreenContent(
    selectedSample: SampleComponent,
    showMenuButton: Boolean,
    onMenuClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(UiDimensSmall)
                    ) {
                        Text(
                            text = stringResource(R.string.sample_catalog_title),
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = stringResource(selectedSample.titleRes),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    if (showMenuButton) {
                        IconButton(onClick = onMenuClick) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(
                                    R.string.sample_navigation_menu
                                )
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            SampleContent(
                component = selectedSample
            )
        }
    }
}
