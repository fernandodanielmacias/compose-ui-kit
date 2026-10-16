package io.github.ordonovus.sample.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.github.ordonovus.sample.settings.SampleThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStore instance used to persist sample application preferences.
 */
private val Context.sampleDataStore by preferencesDataStore(
    name = "sample_preferences"
)

/**
 * Manages persistent preferences for the sample application.
 *
 * Provides an observable theme preference and supports updating
 * the selected appearance mode.
 *
 * @param context Application context used to access DataStore.
 */
internal class SamplePreferencesRepository(
    context: Context
) {

    private val dataStore = context.applicationContext.sampleDataStore

    /**
     * Emits the persisted theme mode whenever its value changes.
     *
     * Defaults to [io.github.ordonovus.sample.settings.SampleThemeMode.System] when no preference
     * has been stored.
     */
    val themeMode: Flow<SampleThemeMode> = dataStore.data.map { preferences ->
        val savedMode = preferences[THEME_MODE_KEY]

        SampleThemeMode.entries.firstOrNull { mode ->
            mode.name == savedMode
        } ?: SampleThemeMode.System
    }

    /**
     * Persists the selected theme mode.
     *
     * @param mode Theme mode to store.
     */
    suspend fun setThemeMode(mode: SampleThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = mode.name
        }
    }

    private companion object {
        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
    }
}