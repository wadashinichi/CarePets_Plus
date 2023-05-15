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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Notification
import com.example.carepets_plus.database.NotificationRepository
import com.example.carepets_plus.database.PetRepository
import com.example.carepets_plus.databinding.FragmentReminderBinding
import com.example.carepets_plus.mainpart.TrackerActivity
import java.util.*

class ReminderFragment : Fragment() {

    private lateinit var binding: FragmentReminderBinding
//    private val NOTIFICATION_REMINDER_NIGHT = 1
    private val CHANNEL_ID = "petsCareChannel"
    private lateinit var calendar: Calendar
    private lateinit var res: NotificationRepository
    private var walkNotification: Notification? = null
    private lateinit var trackerActivity: TrackerActivity
    private var id: Int = 0
    private lateinit var resPet: PetRepository
    private var petName: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReminderBinding.inflate(layoutInflater)
        res = NotificationRepository(requireContext())
        resPet = PetRepository(requireContext())
        trackerActivity = activity as TrackerActivity
        id = trackerActivity.getPetId()
        petName = resPet.getPetById(id)?.name.toString()
        setTakeTime()
        createNotificationChannel()
        getAllNotification()
        setDefaultState()

        setChangeSwitch()


        return binding.root
    }
    private fun getAllNotification() {
        walkNotification = res.getNotificationByName("walk", id)
    }
    private fun setDefaultState() {
        if (walkNotification != null) {
            val state: Int = walkNotification!!.state
            if (state == 1) {
                binding.switchWalk.isChecked = true
            }
            binding.walk.text = walkNotification!!.time
        }
    }
    private fun connectBroadcast(name: String, hour: Int, minute: Int, id: Int, petName: String) {
        var i: Intent = Intent(requireContext(), Broadcast::class.java)
        i.putExtra("name", name)
        i.putExtra("id", id)
        i.putExtra("petName", petName)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(requireContext(), 0, i, 0)
//        createNotificationChannel()

        val alarmManager: AlarmManager = requireContext().getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
//        val time: Long = getTime(18, 45, 25, 4, 2023)
//        val time: Long = mill
        val calendar = Calendar.getInstance()
        takeTime(hour,  minute, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 24*60*60*1000, pendingIntent)
    }
    private fun setTakeTime() {            // thiet dat khi nguoi dung chon thoi gian
        binding.breakfast.setOnClickListener {view: View ->
            setTime(view, "breakfast")
        }
        binding.lunch.setOnClickListener {view: View ->
            setTime(view, "lunch")
        }
        binding.dinner.setOnClickListener {view: View ->
            setTime(view, "dinner")
        }
        binding.snack.setOnClickListener {view: View ->
            setTime(view, "snack")
        }
        binding.walk.setOnClickListener {view: View ->
            setTime(view, "walk")
            if (binding.switchWalk.isChecked) binding.switchWalk.isChecked = false
        }

    }
    private fun setChangeSwitch() {    // khi nguoi dung bat tat switch
        binding.switchMeal.setOnClickListener {
            if (binding.switchMeal.isChecked) {

            }
        }
        binding.switchWalk.setOnClickListener {
            if (binding.switchWalk.isChecked) {                       // su kien khi bat switch
                if (binding.walk.text != "") { // neu walk time da co gia tri
                    if (walkNotification != null) { // neu walk da ton tai trong csdl ---> cap nhat trang thai va gio
                        val newNote: Notification = Notification(
                            walkNotification!!.noteId,
                            walkNotification!!.petId,
                            walkNotification!!.name,
                            binding.walk.text.toString(),
                        1)
                        res.updateNotification(newNote)
//                        val calendar = Calendar.getInstance()
                        val strList: List<String> = binding.walk.text.split(":")
//                        binding.editBreakfast.text = "${strList[0].toInt()} - ${strList[1].toInt()} - $id - $petName - ${calendar.get(Calendar.DAY_OF_MONTH)} - ${calendar.get(Calendar.MONTH) + 1}"

                        connectBroadcast("walk", strList[0].toInt(), strList[1].toInt(), id, petName)    // ----------> tao (cap nhat thong bao)
                    } else {                        // neu walk chua ton tai trong csdl ---> insert 1 notification moi
                        val newNote: Notification = Notification(
                            null,
                            trackerActivity.getPetId(),
                            "walk",
                            binding.walk.text.toString(),
                            1)
                        binding.editBreakfast.text = "done"

                        res.insertNotification(newNote)
                        val strList: List<String> = binding.walk.text.split(":")
                        connectBroadcast("walk", strList[0].toInt(), strList[1].toInt(), id, petName)    // -------------> tao thong bao
                    }
                } else {
                    // neu walk time chua co gia tri ---> khong lam gi ca
                }
            } else {
                if (walkNotification != null) {
                    val newNote: Notification = Notification(
                        walkNotification!!.noteId,
                        walkNotification!!.petId,
                        walkNotification!!.name,
                        walkNotification!!.time,
                        0)
                    res.updateNotification(newNote)
                    turnOffNotification("walk", id) // tat thong bao
                }
            }
        }
    }


    private fun takeTime(hour: Int, minute: Int, day: Int, month: Int, year: Int) {
        calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // if alarm time has already passed, increment day by 1
        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }
    }
    private fun setTime(view: View, title: String) {         // nguoi dung chon tung loai thoi gian
        val clock = Calendar.getInstance()
        var hour = clock.get(Calendar.HOUR)
        var minute = clock.get(Calendar.MINUTE)
        TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { view, hour, minute ->
            when (title) {
                "breakfast" -> binding.editBreakfast.text = "$hour:$minute"
                "lunch" -> binding.editLunch.text = "$hour:$minute"
                "dinner" -> binding.editDinner.text = "$hour:$minute"
                "snack" -> binding.editSnack.text = "$hour:$minute"
                "walk" -> binding.walk.text = "$hour:$minute"
            }
        }, hour, minute, true)
            .show()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel.
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            val notificationManager: NotificationManager = requireContext().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(mChannel)
        }
    }
    private fun turnOffNotification(name: String, id: Int) {
        // Get a reference to the notification manager
        val notificationManager = NotificationManagerCompat.from(requireContext())
        var notificationID = when (name) {
            "breakfast" -> 100 + id
            "lunch" -> 200 + id
            "dinner" -> 300 + id
            "snack" -> 400 + id
            "walk" -> 500 + id
            else -> 0 + id
        }
        // Cancel the notification with the given ID
        notificationManager.cancel(notificationID)
    }
}