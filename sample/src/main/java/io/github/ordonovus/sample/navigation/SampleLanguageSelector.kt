
package io.github.ordonovus.sample.navigation

import android.app.LocaleManager
import android.os.LocaleList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.sample.R
import io.github.ordonovus.theme.UiDimensNone
import io.github.ordonovus.theme.UiDimensSmall
import io.github.ordonovus.theme.UiDimensXXLarge

/**
 * Displays the language selector for the sample application.
 *
 * Uses Android's per-app language preferences to apply and persist
 * the selected locale.
 */
@Composable
internal fun SampleLanguageSelector() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val localeManager = context.getSystemService(LocaleManager::class.java)

    var expanded by rememberSaveable { mutableStateOf(false) }

    val selectedLanguage = localeManager
        .applicationLocales
        .get(0)
        ?.language
        ?: configuration.locales.get(0)?.language
        ?: "es"

    val languageLabel = when (selectedLanguage) {
        "en" -> stringResource(R.string.sample_language_english)
        else -> stringResource(R.string.sample_language_spanish)
    }

    Box {

        TextButton(
            onClick = { expanded = true },
            modifier = Modifier
                .height(UiDimensXXLarge)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentPadding = PaddingValues(
                horizontal = UiDimensSmall,
                vertical = UiDimensNone
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(UiDimensSmall)
            ) {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = languageLabel,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelLarge
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("es", "en").forEach { languageTag ->
                val labelRes = when (languageTag) {
                    "en" -> R.string.sample_language_english
                    else -> R.string.sample_language_spanish
                }

                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(labelRes))
                    },
                    onClick = {
                        expanded = false

                        if (selectedLanguage != languageTag) {
                            localeManager.applicationLocales =
                                LocaleList.forLanguageTags(languageTag)
                        }
                    },
                    trailingIcon = {
                        if (selectedLanguage == languageTag) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }
        }
    }
}