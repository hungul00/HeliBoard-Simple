package lingu.keyboard.keyboard.emoji

import android.content.Context
import lingu.keyboard.latin.settings.Defaults
import lingu.keyboard.latin.settings.Settings
import lingu.keyboard.latin.utils.Log
import lingu.keyboard.latin.utils.prefs

object SupportedEmojis {
    private val unsupportedEmojis = hashSetOf<String>()

    fun load(context: Context) {
        val maxSdk = context.prefs().getInt(Settings.PREF_EMOJI_MAX_SDK, Defaults.PREF_EMOJI_MAX_SDK)
        unsupportedEmojis.clear()
        context.assets.open("emoji/minApi.txt").reader().readLines().forEach {
            val s = it.split(" ")
            val minApi = s.first().toInt()
            if (minApi > maxSdk)
                unsupportedEmojis.addAll(s.drop(1))
        }
    }

    fun isUnsupported(emoji: String) = emoji in unsupportedEmojis
}
