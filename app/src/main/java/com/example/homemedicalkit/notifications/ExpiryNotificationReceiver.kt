package com.example.homemedicalkit.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.homemedicalkit.R
import com.example.homemedicalkit.featureMedicine.domain.model.Medicine
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.MedicineUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class ExpiryNotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var medicineUseCases: MedicineUseCases

    override fun onReceive(context: Context, intent: Intent) {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 3)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        runBlocking {
            val expiredMedicines = medicineUseCases.getAllMedicines().first()
                .filter { it.medicineDate == today.timeInMillis }

            if (expiredMedicines.isNotEmpty()) {
                sendNotification(context, expiredMedicines)
            }
        }
    }

    private fun sendNotification(context: Context, expiredMedicines: List<Medicine>) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "expiry_channel"
        val channelName = "Expiry Notification"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val medicineNames = expiredMedicines.joinToString(" ") { it.medicineName }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Срок годности лекарств")
            .setContentText("Истекает срок годности: $medicineNames")
            .setSmallIcon(R.mipmap.icon_home_medkit_round)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}
