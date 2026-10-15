
package io.github.ordonovus.sample.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.settings.SampleThemeMode
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensXXLarge
import androidx.compose.foundation.isSystemInDarkTheme

/**
 * Displays a dropdown menu for selecting the sample application's
 * appearance mode.
 *
 * The trigger icon reflects the effective light or dark appearance,
 * while the menu indicates the selected theme preference.
 *
 * @param selectedMode Currently selected theme mode.
 * @param onModeSelected Callback invoked when a theme mode is selected.
 */
@Composable
internal fun SampleThemeSelector(
    selectedMode: SampleThemeMode,
    onModeSelected: (SampleThemeMode) -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val darkTheme = selectedMode.isDarkTheme(
        systemDarkTheme = isSystemInDarkTheme()
    )

    val colorScheme = MaterialTheme.colorScheme

    Box {
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .size(UiDimensXXLarge)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(colorScheme.primaryContainer)
        ) {
            Icon(
                imageVector = if (darkTheme) {
                    Icons.Default.DarkMode
                } else {
                    Icons.Default.LightMode
                },
                contentDescription = stringResource(
                    R.string.sample_theme_change
                ),
                modifier = Modifier.size(UiDimensLarge),
                tint = colorScheme.onPrimaryContainer
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SampleThemeMode.entries.forEach { mode ->

                val labelRes = when (mode) {
                    SampleThemeMode.System -> R.string.sample_theme_system
                    SampleThemeMode.Light -> R.string.sample_theme_light
                    SampleThemeMode.Dark -> R.string.sample_theme_dark
                }

                val icon = when (mode) {
                    SampleThemeMode.System -> Icons.Default.Computer
                    SampleThemeMode.Light -> Icons.Default.LightMode
                    SampleThemeMode.Dark -> Icons.Default.DarkMode
                }

                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(labelRes)
                        )
                    },
                    onClick = {
                        expanded = false
                        onModeSelected(mode)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (selectedMode == mode) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = colorScheme.primary
                            )
                        }
                    }
                )
            }
        }
    }
}