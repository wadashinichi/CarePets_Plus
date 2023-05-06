package com.example.carepets_plus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.carepets_plus.sourceport.petlist.ListPetActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            var i: Intent = Intent()
            i.setClass(this, ListPetActivity::class.java)
            startActivity(i)
        }, 1000)
    }
}