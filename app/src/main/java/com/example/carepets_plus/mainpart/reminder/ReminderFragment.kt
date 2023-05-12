package com.example.carepets_plus.mainpart.reminder

import android.app.*
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.carepets_plus.R
import com.example.carepets_plus.databinding.FragmentReminderBinding
import java.util.*

class ReminderFragment : Fragment() {

    private lateinit var binding: FragmentReminderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReminderBinding.inflate(layoutInflater)

        createNotificationChannel()
        scheduleNotification()
        return binding.root
    }
    private fun createNotificationChannel() {
        val name = "Notify Channel"
        val desc = "A Description of the Channel"
        val important = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, important)
        channel.description = desc
        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    private fun scheduleNotification() {
        val intent: Intent = Intent(requireContext().applicationContext, Broadcast::class.java)
        val title = "Title of notification"
        val message = "Message of notification"
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext().applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var time: Long = 0L
        time = getTime(20, 3, 11, 5, 2023)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, message)
    }
    private fun getTime(hour: Int, minute: Int, day: Int, month: Int, year: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }
    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(requireContext().applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(requireContext().applicationContext)

        AlertDialog.Builder(requireContext())
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: $title \nMessage: $message \nAt: ${dateFormat.format(date)} ${timeFormat.format(date)}")
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }
}