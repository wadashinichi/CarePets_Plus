package com.example.carepets_plus.mainpart.home.modify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carepets_plus.database.Pet
import com.example.carepets_plus.database.PetRepository
import com.example.carepets_plus.databinding.ActivityAddPetBinding
import com.example.carepets_plus.databinding.ActivityModifyInformationBinding
import com.example.carepets_plus.mainpart.TrackerActivity
import com.example.carepets_plus.sourceport.petadd.AddPetActivity

class ModifyInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPetBinding
    private lateinit var res: PetRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPetBinding.inflate(layoutInflater)
        res = PetRepository(this)
        setContentView(binding.root)
        setToolBar()

        val intent: Intent = intent
        val id = intent.getIntExtra("petId", 0)
        displayCurrentInfo(id)
        saveModifyInfo(id)
    }

    private fun setToolBar() {
        setSupportActionBar(binding.toolBar)
        binding.toolBar.title = "Modify information"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun displayCurrentInfo(id: Int) {
        val pet: Pet? = res.getPetById(id)

        if (pet != null) {
            // display img
            binding.editName.setText(pet.name.toString())
            binding.editBirth.text = pet.birth.toString()
            binding.editSpecies.setText(pet.species.toString())
        }

    }
    private fun saveModifyInfo(id: Int) {
        binding.btnSubmit.setOnClickListener {
            val pet: Pet = Pet(
                id,
                binding.editName.text.toString(),
                null,                  // change img
                binding.editBirth.text.toString(),
                binding.editSpecies.text.toString())
            res.updatePet(pet)
            reDirect(id)
        }

    }
    private fun reDirect(id: Int) {
        val i: Intent = Intent(this, TrackerActivity::class.java)
        i.putExtra("petId", id)
        startActivity(i)
    }
}