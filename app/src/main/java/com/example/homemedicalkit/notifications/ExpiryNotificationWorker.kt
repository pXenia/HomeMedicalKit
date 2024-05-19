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
import com.example.homemedicalkit.featureMedicine.domain.model.Medicine
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.MedicineUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.util.Calendar

@HiltWorker
class ExpiryNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val medicineUseCases: MedicineUseCases
) : CoroutineWorker(context, workerParams) {

    private val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 3)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    override suspend fun doWork(): Result {

        val expiredMedicines =  medicineUseCases.getAllMedicines().first()
            .filter { it.medicineDate == today.timeInMillis }

        if (expiredMedicines.isNotEmpty()) {
            sendNotification(expiredMedicines)
        }

        return Result.success()
    }

    private fun sendNotification(expiredMedicines: List<Medicine>) {

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "expiry_channel"
        val channelName = "Expiry Notification"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val medicineNames = expiredMedicines.joinToString(", ") { it.medicineName }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Срок годности лекарств")
            .setContentText("Истекает срок годности: $medicineNames")
            .setSmallIcon(R.drawable.first_aid_kit_bro) // Ensure this drawable exists
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    @AssistedFactory
    interface Factory {
        fun create(context: Context, workerParams: WorkerParameters): ExpiryNotificationWorker
    }
}