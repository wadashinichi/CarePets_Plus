package com.example.carepets_plus.mainpart.home.modify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carepets_plus.databinding.ActivityModifyInformationBinding

class ModifyInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifyInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}