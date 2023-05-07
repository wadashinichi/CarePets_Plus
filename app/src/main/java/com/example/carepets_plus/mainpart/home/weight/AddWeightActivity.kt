package com.example.carepets_plus.mainpart.home.weight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carepets_plus.R
import com.example.carepets_plus.databinding.ActivityAddWeightBinding

class AddWeightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWeightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}