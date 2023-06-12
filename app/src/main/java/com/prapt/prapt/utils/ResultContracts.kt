package com.prapt.prapt.utils
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
class ResultContracts {
    class SelectFile : ActivityResultContract<String, Uri?>() {
        override fun createIntent(context: Context, input: String): Intent =
            Intent(Intent.ACTION_GET_CONTENT).apply {
                type = input
            }
        override fun parseResult(resultCode: Int, intent: Intent?): Uri? = when (resultCode) {
            Activity.RESULT_OK -> intent?.data
            else -> null
        }
    }
    class ActivityResultResponse : ActivityResultContract<Intent, Intent?>() {
        override fun createIntent(context: Context, intent: Intent): Intent = intent
        override fun parseResult(resultCode: Int, intent: Intent?): Intent? {
            if (resultCode == Activity.RESULT_OK) {
                return intent
            }
            return null
        }
    }
}