// SPDX-License-Identifier: GPL-3.0-only
package lingu.keyboard.settings.screens

import android.content.Context
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import lingu.keyboard.latin.R
import lingu.keyboard.settings.SettingsContainer
import lingu.keyboard.settings.SettingsWithoutKey
import lingu.keyboard.settings.Setting
import lingu.keyboard.settings.preferences.Preference
import lingu.keyboard.settings.SearchSettingsScreen
import lingu.keyboard.settings.SettingsActivity
import lingu.keyboard.settings.Theme
import lingu.keyboard.settings.previewDark
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Composable
fun OpenSourceScreen(
    onClickBack: () -> Unit,
) {
    val items = listOf(
        SettingsWithoutKey.OPEN_SOURCE,
    )
    SearchSettingsScreen(
        onClickBack = onClickBack,
        title = stringResource(R.string.license),
        settings = items
    )
}
fun readStringFromAsset(context: Context, fileName: String): String {
    return try {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
            stringBuilder.append("\n") // Add newline if you want to preserve line breaks
        }
        reader.close()
        stringBuilder.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun createOpenSourceSettings(context: Context) = listOf(
    Setting(context, SettingsWithoutKey.OPEN_SOURCE, R.string.english_ime_name, R.string.english_ime_name) {
        Preference(
            name = it.title,
            description = readStringFromAsset(context, "license.txt"),
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
