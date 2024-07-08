package com.example.finalproject.ui.leftbar.presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityLeftBarBinding
import com.example.finalproject.ui.home.presenter.HomeActivity

class LeftBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeftBarBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        val initialFragment = intent.getIntExtra("initialFragment", R.id.imagesFragment)
        navController.navigate(initialFragment)

        setupButtonNavigation()
        setupBackIcon()
    }

    private fun setupButtonNavigation() {
        binding.navImagesFragment.setOnClickListener {
            navController.navigate(R.id.imagesFragment)
        }
        binding.navDescriptionFragment.setOnClickListener {
            navController.navigate(R.id.descriptionFragment)
        }
        binding.navFinancingFragment.setOnClickListener {
            navController.navigate(R.id.financingFragment)
        }
        binding.navCommentsFragment.setOnClickListener {
            navController.navigate(R.id.commentsFragment)
        }
    }

    private fun setupBackIcon() {
        binding.backIcon.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}