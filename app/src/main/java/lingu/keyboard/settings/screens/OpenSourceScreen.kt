// SPDX-License-Identifier: GPL-3.0-only
package lingu.keyboard.settings.screens

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import lingu.keyboard.latin.BuildConfig
import lingu.keyboard.latin.R
import lingu.keyboard.latin.common.Links
import lingu.keyboard.latin.settings.DebugSettings
import lingu.keyboard.latin.settings.Defaults
import lingu.keyboard.latin.utils.Log
import lingu.keyboard.latin.utils.SpannableStringUtils
import lingu.keyboard.latin.utils.getActivity
import lingu.keyboard.latin.utils.prefs
import lingu.keyboard.settings.SettingsContainer
import lingu.keyboard.settings.SettingsWithoutKey
import lingu.keyboard.settings.Setting
import lingu.keyboard.settings.preferences.Preference
import lingu.keyboard.settings.SearchSettingsScreen
import lingu.keyboard.settings.SettingsActivity
import lingu.keyboard.settings.Theme
import lingu.keyboard.settings.previewDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun OpenSourceScreen(
    onClickBack: () -> Unit,
) {
    val items = listOf(
        SettingsWithoutKey.APP,
    )
    SearchSettingsScreen(
        onClickBack = onClickBack,
        title = stringResource(R.string.license),
        settings = items
    )
}

fun createOpenSourceSettings(context: Context) = listOf(
    Setting(context, SettingsWithoutKey.APP, R.string.english_ime_name, R.string.app_slogan) {
        Preference(
            name = it.title,
            description = it.description,
            onClick = { },
        )
    },
)

@Preview
@Composable
private fun Preview() {
    SettingsActivity.settingsContainer = SettingsContainer(LocalContext.current)
    Theme(previewDark) {
        Surface {
            OpenSourceScreen {  }
        }
    }
}
