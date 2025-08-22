package lingu.keyboard.latin.utils

import android.content.Context
import android.content.Intent
import lingu.keyboard.keyboard.internal.keyboard_parser.floris.KeyCode
import lingu.keyboard.latin.inputlogic.InputLogic
import lingu.keyboard.latin.utils.Log.i

object IntentUtils {
    val TAG: String = InputLogic::class.java.simpleName
    private val ACTION_SEND_INTENT = "lingu.keyboard.latin.ACTION_SEND_INTENT"
    private val EXTRA_NUMBER = "EXTRA_NUMBER"

    @JvmStatic
    fun handleSendIntentKey(context: Context, mKeyCode: Int) {
        val intentNumber = (KeyCode.SEND_INTENT_ONE + 1) - mKeyCode;

        val intent: Intent = Intent(ACTION_SEND_INTENT).apply {
            putExtra(EXTRA_NUMBER, intentNumber)
        }

        context.sendBroadcast(intent)
        i(TAG, "Sent broadcast for intent number: $intentNumber")
    }
}

