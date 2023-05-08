package com.example.carepets_plus.sourceport.petlist

import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.database.Pet
import com.example.carepets_plus.database.PetRepository
import com.example.carepets_plus.databinding.ActivityListPetBinding
import com.example.carepets_plus.mainpart.TrackerActivity
import com.example.carepets_plus.sourceport.petadd.AddPetActivity

class ListPetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPetBinding
    private lateinit var plist: MutableList<Pet>
    private lateinit var adapter: PetListAdapter
    private lateinit var res: PetRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        res = PetRepository(this)
        if (res.getAllPet() != null) {
            plist = res.getAllPet()!!
            displayList(plist)
            binding.btnDel.text = "s"
        }
        binding.btnAdd.setOnClickListener {
            moveToAdd()
        }
        binding.btnDel.setOnClickListener {

        }

    }

    private fun displayList(plist: List<Pet>) {
        adapter = PetListAdapter(plist, this)
        binding.rvPetList.adapter = adapter
        binding.rvPetList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var decorate: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvPetList.addItemDecoration(decorate)
    }
    private fun moveToAdd() {
        var i: Intent = Intent()
        i.setClass(this, AddPetActivity::class.java)
        startActivity(i)
    }

}