package com.example.homemedicalkit.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.homemedicalkit.R
import com.example.homemedicalkit.featureMedicine.domain.repository.MedicineRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@HiltWorker
class ExpiryNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val medicineRepository: MedicineRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        sendNotification()

        return Result.success()
    }

    private fun sendNotification() {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "expiry_channel"
        val channelName = "Expiry Notification"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Expired Medicines")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText("The following medicines have expired: nnnnnnn")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    @AssistedFactory
    interface Factory {
        fun create(context: Context, workerParams: WorkerParameters): ExpiryNotificationWorker
    }
}