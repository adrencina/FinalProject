package com.example.finalproject.ui.leftbar.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityLeftBarBinding
import com.google.android.material.navigation.NavigationView

class LeftBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeftBarBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.root
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val navigationView = binding.navigationView
        NavigationUI.setupWithNavController(navigationView, navController)

        setupDrawerContent(navigationView)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_commentsFragment -> {
                    navController.navigate(R.id.commentsFragment)
                }

                R.id.nav_financingFragment -> {
                    navController.navigate(R.id.financingFragment)
                }

                R.id.nav_descriptionFragment -> {
                    navController.navigate(R.id.descriptionFragment)
                }

                R.id.nav_imagesFragment -> {
                    navController.navigate(R.id.imagesFragment)
                }
            }
            true
        }
    }
}