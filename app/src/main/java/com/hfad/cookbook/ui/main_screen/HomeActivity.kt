package com.hfad.cookbook.ui.main_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hfad.cookbook.R
import com.hfad.cookbook.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupNavigation()
        //setupToolbar()
    }

    private fun setupNavigation() {
        val bottomNav = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.menu_recipes,
            R.id.menu_featured
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNav.setupWithNavController(navController)
    }

//    private fun setupToolbar() {
//        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
//            binding.appbar.setExpanded(true, false)
//            true
//        }
//    }
}