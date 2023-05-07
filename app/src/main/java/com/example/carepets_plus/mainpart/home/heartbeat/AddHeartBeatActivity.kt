package com.example.carepets_plus.mainpart.home.heartbeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carepets_plus.databinding.ActivityAddHeartBeatBinding

class AddHeartBeatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddHeartBeatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHeartBeatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}