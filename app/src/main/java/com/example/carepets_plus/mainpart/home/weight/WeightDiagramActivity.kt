package com.example.carepets_plus.mainpart.home.weight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carepets_plus.databinding.ActivityWeightDiagramBinding

class WeightDiagramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeightDiagramBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightDiagramBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}