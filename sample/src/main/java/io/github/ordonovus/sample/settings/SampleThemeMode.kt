package io.github.ordonovus.sample.settings

/**
 * Available appearance modes for the sample application.
 */
enum class SampleThemeMode {
    System,
    Light,
    Dark;

    /**
     * Resolves whether the dark theme should be enabled.
     *
     * @param systemDarkTheme Whether the system is using dark mode.
     * @return True if the dark theme should be applied.
     */
    fun isDarkTheme(systemDarkTheme: Boolean): Boolean {
        return when (this) {
            System -> systemDarkTheme
            Light -> false
            Dark -> true
        }
    }
}
