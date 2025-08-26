// SPDX-License-Identifier: GPL-3.0-only
package lingu.keyboard.settings.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import lingu.keyboard.latin.R
import lingu.keyboard.latin.utils.JniUtils
import lingu.keyboard.latin.utils.SubtypeLocaleUtils.displayName
import lingu.keyboard.latin.utils.SubtypeSettings
import lingu.keyboard.settings.NextScreenIcon
import lingu.keyboard.settings.SearchSettingsScreen
import lingu.keyboard.settings.Theme
import lingu.keyboard.settings.initPreview
import lingu.keyboard.settings.preferences.Preference
import lingu.keyboard.settings.previewDark

@Composable
fun MainSettingsScreen(
    onClickAbout: () -> Unit,
//    onClickTextCorrection: () -> Unit,
    onClickPreferences: () -> Unit,
//    onClickToolbar: () -> Unit,
//    onClickGestureTyping: () -> Unit,
//    onClickAdvanced: () -> Unit,
//    onClickAppearance: () -> Unit,
    onClickLanguage: () -> Unit,
//    onClickLayouts: () -> Unit,
//    onClickDictionaries: () -> Unit,
    onClickOpenSource: () -> Unit,
    onClickBack: () -> Unit,
) {
    SearchSettingsScreen(
        onClickBack = onClickBack,
        title = stringResource(R.string.ime_settings),
        settings = emptyList(),
    ) {
        val enabledSubtypes = SubtypeSettings.getEnabledSubtypes(true)
        Scaffold(contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)) { innerPadding ->
            Column(
                Modifier.verticalScroll(rememberScrollState()).then(Modifier.padding(innerPadding))
            ) {
                Preference(
                    name = stringResource(R.string.language_and_layouts_title),
                    description = enabledSubtypes.joinToString(", ") { it.displayName() },
                    onClick = onClickLanguage,
                    icon = R.drawable.ic_settings_languages
                )
                Preference(
                    name = stringResource(R.string.settings_screen_preferences),
                    onClick = onClickPreferences,
                    icon = R.drawable.ic_settings_preferences
                ) { NextScreenIcon() }
//                Preference(
//                    name = stringResource(R.string.settings_screen_appearance),
//                    onClick = onClickAppearance,
//                    icon = R.drawable.ic_settings_appearance
//                ) { NextScreenIcon() }
//                Preference(
//                    name = stringResource(R.string.settings_screen_toolbar),
//                    onClick = onClickToolbar,
//                    icon = R.drawable.ic_settings_toolbar
//                ) { NextScreenIcon() }
//                if (JniUtils.sHaveGestureLib)
//                    Preference(
//                        name = stringResource(R.string.settings_screen_gesture),
//                        onClick = onClickGestureTyping,
//                        icon = R.drawable.ic_settings_gesture
//                    ) { NextScreenIcon() }
//                Preference(
//                    name = stringResource(R.string.settings_screen_correction),
//                    onClick = onClickTextCorrection,
//                    icon = R.drawable.ic_settings_correction
//                ) { NextScreenIcon() }
//                Preference(
//                    name = stringResource(R.string.settings_screen_secondary_layouts),
//                    onClick = onClickLayouts,
//                    icon = R.drawable.ic_ime_switcher
//                ) { NextScreenIcon() }
//                Preference(
//                    name = stringResource(R.string.dictionary_settings_category),
//                    onClick = onClickDictionaries,
//                    icon = R.drawable.ic_dictionary
//                ) { NextScreenIcon() }
//                Preference(
//                    name = stringResource(R.string.settings_screen_advanced),
//                    onClick = onClickAdvanced,
//                    icon = R.drawable.ic_settings_advanced
//                ) { NextScreenIcon() }
                Preference(
                    name = stringResource(R.string.license),
                    onClick = onClickOpenSource,
                    icon = R.drawable.ic_settings_about_license
                ) { NextScreenIcon() }
                Preference(
                    name = stringResource(R.string.settings_screen_about),
                    onClick = onClickAbout,
                    icon = R.drawable.ic_settings_about
                ) { NextScreenIcon() }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    initPreview(LocalContext.current)
    Theme(previewDark) {
        Surface {
            MainSettingsScreen({}, {}, {}, {}, {})
        }
    }
}
