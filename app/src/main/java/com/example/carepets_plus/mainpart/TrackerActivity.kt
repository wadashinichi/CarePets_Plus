package com.example.carepets_plus.mainpart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.carepets_plus.R
import com.example.carepets_plus.database.PetRepository
import com.example.carepets_plus.databinding.ActivityTrackerBinding
import com.example.carepets_plus.mainpart.home.HomeFragment
import com.example.carepets_plus.mainpart.reminder.ReminderFragment
import com.example.carepets_plus.mainpart.search.SearchFragment
import com.example.carepets_plus.sourceport.aboutus.AboutUsActivity
import com.example.carepets_plus.sourceport.petlist.ListPetActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class TrackerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityTrackerBinding
    private lateinit var res: PetRepository
    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent: Intent = intent
        id = intent.getIntExtra("petId", 0)

        setSupportActionBar(binding.toolBar)
        var toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolBar, R.string.drawer_open, R.string.drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        // bat su kien khi kich vao item
        binding.navigationView.setNavigationItemSelectedListener(this)
        replaceFragment(HomeFragment())
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.homeFragment ->
                    replaceFragment(HomeFragment())
                R.id.searchFragment -> replaceFragment(SearchFragment())
                else ->
                    replaceFragment(ReminderFragment())
            }
            true
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        when (id) {
            R.id.listPetActivity -> {
                var i: Intent = Intent()
                i.setClass(this, ListPetActivity::class.java)
                startActivity(i)
            }
            R.id.aboutUsActivity -> {
                var i: Intent = Intent()
                i.setClass(this, AboutUsActivity::class.java)
                startActivity(i)
            }
        }
        binding.navigationView.setCheckedItem(id)
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    // thuc hien khi nhan nut back
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    private fun replaceFragment(fg: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fg)
        transaction.commit()
    }
    fun getPetId(): Int {
        return id
    }
}