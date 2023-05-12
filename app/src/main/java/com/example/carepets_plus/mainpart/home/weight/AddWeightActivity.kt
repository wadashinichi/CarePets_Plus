package com.example.carepets_plus.mainpart.home.weight

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Weight
import com.example.carepets_plus.database.WeightRepository
import com.example.carepets_plus.databinding.ActivityAddWeightBinding

class AddWeightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWeightBinding
    private lateinit var res: WeightRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)
        res = WeightRepository(this)
        val id = getIdFromIntent()

        setToolBar()
        setForm(id)
    }
    private fun getIdFromIntent(): Int {
        val intent: Intent = intent
        return intent.getIntExtra("petId", 0)
    }
    private fun setToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun setForm(id: Int) {
        binding.dateCardView.setOnClickListener {view: View ->
            takeDate(view)
        }
        binding.timeCardView.setOnClickListener { view: View ->
            takeTime(view)
        }
        binding.btnSubmit.setOnClickListener {
            saveWeight(id)
        }
    }
    private fun takeDate(view: View) {
        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var date: String = ""
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, day ->
            binding.editDate.text = "$day/${month+1}/$year"
        }, year, month, day)
            .show()
    }
    private fun takeTime(view: View) {
        val clock = Calendar.getInstance()
        var hour = clock.get(Calendar.HOUR)
        var minute = clock.get(Calendar.MINUTE)
        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hour, minute ->
            binding.editTime.text = "$hour:$minute"
        }, hour, minute, true)
            .show()
    }
    private fun saveWeight(id: Int) {
        if (binding.editDate.text.toString() == "") {
            binding.editDate.error = "Please choose date!"
        } else if (binding.editTime.text.toString() == "") {
            binding.editTime.error = "Please choose time!"
        } else if (binding.editResult.text.toString() == "") {
            binding.editResult.error = "Please input weight result!"
        } else {
            val weight: Weight = Weight(
                null, id,
                binding.editDate.text.toString(),
                binding.editTime.text.toString(),
                binding.editResult.text.toString().toDouble()
            )
            res.insertWeight(weight)
            reDirect(id)
        }
    }
    private fun reDirect(id: Int){
        var i: Intent = Intent(this, WeightDiagramActivity::class.java)
        i.putExtra("petId", id)
        startActivity(i)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}