package ru.eshtykin.acs_monitoring_admin.notification

import android.content.Intent
import android.content.IntentFilter
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(INTENT_FILTER)
        message.data.forEach { entity ->
            intent.putExtra(entity.key, entity.value)
        }
        sendBroadcast(intent)
    }

    companion object {

        const val INTENT_FILTER = "PUSH_EVENT"
        const val KEY_ACTION = "action"
        const val KEY_MESSAGE = "message"

        const val ACTION_SHOW_MESSAGE = "show_message"

        fun newIntentFilter(event: String): IntentFilter =
            IntentFilter().apply { addAction(event) }

    }
}