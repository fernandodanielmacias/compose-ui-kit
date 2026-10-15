package io.github.ordonovus.sample.screen

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import io.github.ordonovus.sample.catalog.DefaultSampleComponent
import io.github.ordonovus.sample.catalog.SampleComponent
import io.github.ordonovus.sample.catalog.SampleContent
import io.github.ordonovus.sample.navigation.SampleNavigationDrawer
import io.github.ordonovus.sample.navigation.SampleTopAppBar
import io.github.ordonovus.sample.settings.SampleThemeMode
import io.github.ordonovus.sample.ui.SampleDrawerWidth
import io.github.ordonovus.sample.ui.SamplePermanentDrawerBreakpoint
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
    initialSample: SampleComponent = DefaultSampleComponent,
    selectedThemeMode: SampleThemeMode,
    onThemeModeSelected: (SampleThemeMode) -> Unit
) {
    var selectedSample by rememberSaveable { mutableStateOf(initialSample) }
    var isDrawerExpanded by rememberSaveable { mutableStateOf(true) }

    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val windowWidth = with(density) {
        windowInfo.containerSize.width.toDp()
    }

    val usePermanentDrawer =
        windowWidth >= SamplePermanentDrawerBreakpoint

    if (usePermanentDrawer) {
        if (isDrawerExpanded) {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet(
                        modifier = Modifier.width(SampleDrawerWidth)
                    ) {
                        SampleNavigationDrawer(
                            selectedSample = selectedSample,
                            onSampleSelected = { selectedSample = it }
                        )
                    }
                }
            ) {
                SampleScreenContent(
                    selectedSample = selectedSample,
                    showMenuButton = true,
                    isPermanentDrawer = true,
                    isDrawerExpanded = isDrawerExpanded,
                    selectedThemeMode = selectedThemeMode,
                    onThemeModeSelected = onThemeModeSelected,
                    onMenuClick = {
                        isDrawerExpanded = false
                    }
                )
            }
        } else {
            SampleScreenContent(
                selectedSample = selectedSample,
                showMenuButton = true,
                isPermanentDrawer = true,
                isDrawerExpanded = false,
                selectedThemeMode = selectedThemeMode,
                onThemeModeSelected = onThemeModeSelected,
                onMenuClick = {
                    isDrawerExpanded = true
                }
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
                    SampleNavigationDrawer(
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
                selectedThemeMode = selectedThemeMode,
                onThemeModeSelected = onThemeModeSelected,
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
 * Displays the selected sample within the common application layout.
 */
@Composable
private fun SampleScreenContent(
    selectedSample: SampleComponent,
    showMenuButton: Boolean,
    isPermanentDrawer: Boolean = false,
    isDrawerExpanded: Boolean = false,
    selectedThemeMode: SampleThemeMode,
    onThemeModeSelected: (SampleThemeMode) -> Unit,
    onMenuClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SampleTopAppBar(
                selectedSample = selectedSample,
                showMenuButton = showMenuButton,
                isPermanentDrawer = isPermanentDrawer,
                isDrawerExpanded = isDrawerExpanded,
                selectedThemeMode = selectedThemeMode,
                onThemeModeSelected = onThemeModeSelected,
                onMenuClick = onMenuClick
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
            SampleContent(component = selectedSample)
        }
    }
}
