// SPDX-License-Identifier: GPL-3.0-only
package lingu.keyboard.settings

import android.content.Context
import lingu.keyboard.keyboard.internal.KeyboardIconsSet
import lingu.keyboard.latin.settings.Settings
import lingu.keyboard.latin.utils.SubtypeSettings

// file is meant for making compose previews work

fun initPreview(context: Context) {
    Settings.init(context)
    SubtypeSettings.init(context)
    SettingsActivity.settingsContainer = SettingsContainer(context)
    KeyboardIconsSet.instance.loadIcons(context)
}

const val previewDark = true
