package com.example.carepets_plus.sourceport.petadd

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Pet
import com.example.carepets_plus.database.PetRepository
import com.example.carepets_plus.databinding.ActivityAddPetBinding
import com.example.carepets_plus.sourceport.petlist.ListPetActivity

class AddPetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPetBinding
    private lateinit var res: PetRepository
    private lateinit var uri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        res = PetRepository(this)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var name: String = ""
        var birth: String = ""
        var species: String = ""
        var img: String = ""
        binding.btnPickImg.setOnClickListener {
            pickImg()

            // image
            img = uri
        }
        binding.editBirth.setOnClickListener {view: View ->
            takeDate(view)
        }
        binding.btnSubmit.setOnClickListener {
            name = binding.editName.text.toString()
            species = binding.editSpecies.text.toString()
            birth = binding.editBirth.text.toString()

            if (name == "") {
                binding.editName.error = "Please input this field!"
                Toast.makeText(this, "Need to input name", Toast.LENGTH_SHORT)
            } else if (birth == "") {
                binding.editBirth.error = "Please choose a day!"
                Toast.makeText(this, "Need to choose birth", Toast.LENGTH_SHORT)
            } else if (species == "") {
                binding.editSpecies.error = "Please input this field!"
                Toast.makeText(this, "Need to input species", Toast.LENGTH_SHORT)
            } else {
                var pet: Pet = Pet(null, name, img, birth, species)
                res.insertPet(pet)
                reDirect()
            }
        }
    }
    private fun takeDate(view: View){
        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var date: String = ""
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, day ->
            binding.editBirth.text = "$day/${month+1}/$year"
        }, year, month, day)
            .show()
    }
    private fun pickImg() {
        var i: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, 3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var img: ImageView = findViewById(R.id.imgPet)

        if (resultCode == RESULT_OK && data != null) {
            var selectedImg: Uri? = data.data
            img.setImageURI(selectedImg)

            uri = selectedImg.toString()

//            if (selectedImg != null) {
//            }

        }
    }
    private fun reDirect() {
        val i: Intent = Intent()
        i.setClass(this, ListPetActivity::class.java)
        startActivity(i)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}