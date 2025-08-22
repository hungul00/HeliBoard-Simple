// SPDX-License-Identifier: GPL-3.0-only

package lingu.keyboard.keyboard.clipboard

interface OnKeyEventListener {

    fun onKeyDown(clipId: Long)

    fun onKeyUp(clipId: Long)

}
