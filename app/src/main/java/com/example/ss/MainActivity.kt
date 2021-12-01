package com.example.ss

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat

import androidx.drawerlayout.widget.DrawerLayout
import com.example.ss.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.mainToolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawerlayout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open, R.string.nav_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "APPortar"

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        val epss = resources.getStringArray(R.array.eps_array)
        val epsArray = ArrayAdapter(applicationContext, R.layout.dropdown_item, epss)
        binding.dropEPS.setAdapter(epsArray)

        val arls = resources.getStringArray(R.array.arl_array)
        val arlArray = ArrayAdapter(applicationContext, R.layout.dropdown_item, arls)
        binding.dropARL.setAdapter(arlArray)

        val afps = resources.getStringArray(R.array.afp_array)
        val afpArray = ArrayAdapter(applicationContext, R.layout.dropdown_item, afps)
        binding.dropAFP.setAdapter(afpArray)

        val nivelR = resources.getStringArray(R.array.risk_array)
        val riskArray = ArrayAdapter(applicationContext, R.layout.dropdown_item, nivelR)
        binding.dropLevel.setAdapter(riskArray)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_one -> print("si")
            R.id.nav_two -> Toast.makeText(applicationContext, "Item 2", Toast.LENGTH_SHORT).show()
            R.id.nav_three -> Toast.makeText(applicationContext, "Item 3", Toast.LENGTH_SHORT)
                .show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}