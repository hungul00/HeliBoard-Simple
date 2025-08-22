// SPDX-License-Identifier: GPL-3.0-only

package lingu.keyboard.keyboard.emoji;

import lingu.keyboard.keyboard.Key;

/**
 * Interface to handle callbacks from child elements
 * such as Emoji buttons and keyboard views.
 */
public interface EmojiViewCallback {

    /**
     * Called when a key is pressed by the user
     */
    void onPressKey(Key key);

    /**
     * Called when a key is released.
     * This may be called without any prior call to {@link EmojiViewCallback#onPressKey(Key)},
     * for example when a key from a popup keys keyboard is selected by releasing touch on it.
     */
    void onReleaseKey(Key key);

    /**
     * Called from keyboard view to get an emoji description
     */
    String getDescription(String emoji);
}
