// SPDX-License-Identifier: GPL-3.0-only
package lingu.keyboard.latin

import lingu.keyboard.latin.common.LocaleUtils.constructLocale
import kotlin.test.Test
import kotlin.test.assertEquals

class LocaleUtilsTest {
    @Test fun createLocales() {
        assertEquals("en_US".constructLocale(), "en-US".constructLocale())
        assertEquals("en_us".constructLocale(), "en-US".constructLocale())
        assertEquals("hi__#Latn".constructLocale(), "hi-Latn".constructLocale())
        assertEquals("hi_zz".constructLocale(), "hi-Latn".constructLocale())
        assertEquals("hi_ZZ".constructLocale(), "hi-Latn".constructLocale())
        assertEquals("zz".constructLocale().toLanguageTag(), "zz")
        assertEquals("zz".constructLocale().toString(), "zz")
    }
}
