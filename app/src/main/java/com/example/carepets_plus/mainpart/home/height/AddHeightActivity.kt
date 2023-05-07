package com.example.carepets_plus.mainpart.home.height

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carepets_plus.databinding.ActivityAddHeightBinding

class AddHeightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddHeightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHeightBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}