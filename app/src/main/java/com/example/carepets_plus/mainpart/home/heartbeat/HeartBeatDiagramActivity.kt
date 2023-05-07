package com.example.carepets_plus.mainpart.home.heartbeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carepets_plus.databinding.ActivityHeightDiagrmaBinding

class HeartBeatDiagramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeightDiagrmaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeightDiagrmaBinding.inflate(layoutInflater)
       setContentView(binding.root)
    }
}