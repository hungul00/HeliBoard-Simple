// SPDX-License-Identifier: GPL-3.0-only
package lingu.keyboard.settings.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lingu.keyboard.latin.R
import lingu.keyboard.latin.common.splitOnWhitespace
import lingu.keyboard.latin.utils.SubtypeLocaleUtils
import lingu.keyboard.latin.utils.SubtypeSettings.getEnabledSubtypes
import lingu.keyboard.latin.utils.SubtypeSettings.getSystemLocales
import lingu.keyboard.latin.utils.getSecondaryLocales
import lingu.keyboard.latin.utils.locale
import lingu.keyboard.settings.NextScreenIcon
import lingu.keyboard.settings.SearchScreen
import lingu.keyboard.settings.SettingsDestination
import lingu.keyboard.settings.Theme
import lingu.keyboard.settings.initPreview
import lingu.keyboard.settings.previewDark
import java.util.Locale
import java.util.TreeSet

@Composable
fun PersonalDictionariesScreen(
    onClickBack: () -> Unit,
) {
    // todo: consider adding "add word" button like old settings (requires additional navigation parameter, should not be hard)
    val ctx = LocalContext.current
    val locales: MutableList<Locale?> = getSortedDictionaryLocales().toMutableList()
    locales.add(0, null)
    SearchScreen(
        onClickBack = onClickBack,
        title = { Text(stringResource(R.string.edit_personal_dictionary)) },
        filteredItems = { term ->
            locales.filter {
                it.getLocaleDisplayNameForUserDictSettings(ctx).replace("(", "")
                    .splitOnWhitespace().any { it.startsWith(term, true) }
            }
        },
        itemContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        SettingsDestination.navigateTo(SettingsDestination.PersonalDictionary + (it?.toLanguageTag() ?: ""))
                    }
                    .heightIn(min = 44.dp)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(it.getLocaleDisplayNameForUserDictSettings(ctx), style = MaterialTheme.typography.bodyLarge)
                NextScreenIcon()
            }
        }
    )
}

fun getSortedDictionaryLocales(): TreeSet<Locale> {
    val sortedLocales = sortedSetOf<Locale>(compareBy { it.toLanguageTag().lowercase() })

    // Add the main language selected in the "Language and Layouts" setting except "No language"
    for (mainSubtype in getEnabledSubtypes(true)) {
        val mainLocale = mainSubtype.locale()
        if (mainLocale.toLanguageTag() != SubtypeLocaleUtils.NO_LANGUAGE) {
            sortedLocales.add(mainLocale)
        }
        // Secondary language is added only if main language is selected
        val enabled = getEnabledSubtypes(false)
        for (subtype in enabled) {
            if (subtype.locale() == mainLocale) sortedLocales.addAll(getSecondaryLocales(subtype.extraValue))
        }
    }

    sortedLocales.addAll(getSystemLocales())
    return sortedLocales
}

@Preview
@Composable
private fun Preview() {
    initPreview(LocalContext.current)
    Theme(previewDark) {
        Surface {
            PersonalDictionariesScreen { }
        }
    }
}
