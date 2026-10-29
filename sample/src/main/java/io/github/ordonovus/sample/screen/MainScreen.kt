package io.github.ordonovus.sample.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import io.github.ordonovus.component.textfield.UiTextFieldIconDefaults
import io.github.ordonovus.component.textfield.UiTextFieldIconTheme
import io.github.ordonovus.sample.catalog.DefaultSampleComponent
import io.github.ordonovus.sample.catalog.SampleComponent
import io.github.ordonovus.sample.catalog.SampleContent
import io.github.ordonovus.sample.navigation.SampleNavigationDrawer
import io.github.ordonovus.sample.navigation.SampleTopAppBar
import io.github.ordonovus.sample.settings.SampleThemeMode
import io.github.ordonovus.sample.ui.feedback.LocalSampleSnackbarHostState
import io.github.ordonovus.sample.ui.layout.SampleDrawerWidth
import io.github.ordonovus.sample.ui.layout.SamplePermanentDrawerBreakpoint
import io.github.ordonovus.ui.keyboard.rememberUiKeyboardController
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

    val keyboardController = rememberUiKeyboardController()

    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val windowWidth = with(density) {
        windowInfo.containerSize.width.toDp()
    }

    val usePermanentDrawer =
        windowWidth >= SamplePermanentDrawerBreakpoint

    if (usePermanentDrawer) {
        PermanentNavigationDrawer(
            drawerContent = {
                if (isDrawerExpanded) {
                    PermanentDrawerSheet(
                        modifier = Modifier.width(SampleDrawerWidth),
                        drawerContainerColor =
                            MaterialTheme.colorScheme.surfaceContainerLow,
                        windowInsets = WindowInsets.statusBars
                    ) {
                        SampleNavigationDrawer(
                            selectedSample = selectedSample,
                            onSampleSelected = {
                                keyboardController.dismiss()
                                selectedSample = it
                            }
                        )
                    }
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
                    keyboardController.dismiss()
                    isDrawerExpanded = !isDrawerExpanded
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
                ModalDrawerSheet(
                    drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ) {
                    SampleNavigationDrawer(
                        selectedSample = selectedSample,
                        onSampleSelected = { component ->
                            keyboardController.dismiss()
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
                    keyboardController.dismiss()

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
 *
 * Provides a shared snackbar host to contextual component demonstrations.
 *
 * @param selectedSample Currently selected sample component.
 * @param showMenuButton Whether the navigation menu button is displayed.
 * @param isPermanentDrawer Whether permanent drawer navigation is available.
 * @param isDrawerExpanded Whether the permanent drawer is expanded.
 * @param selectedThemeMode Currently selected appearance mode.
 * @param onThemeModeSelected Called when the appearance mode changes.
 * @param onMenuClick Called when the navigation menu button is pressed.
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
    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(
        LocalSampleSnackbarHostState provides snackbarHostState
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            },
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
                UiTextFieldIconTheme(
                    colors = UiTextFieldIconDefaults.colors(
                        action = MaterialTheme.colorScheme.primary
                    )
                ) {
                    SampleContent(
                        component = selectedSample
                    )
                }

            }
        }
    }
}