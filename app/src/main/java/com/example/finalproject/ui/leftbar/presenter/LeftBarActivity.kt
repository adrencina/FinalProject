package com.example.finalproject.ui.leftbar.presenter

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@LeftBarActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun setupButtonNavigation() {
        binding.navImagesFragment.setOnClickListener {
            navigateToFragment(R.id.imagesFragment)
        }
        binding.navDescriptionFragment.setOnClickListener {
            navigateToFragment(R.id.descriptionFragment)
        }
        binding.navFinancingFragment.setOnClickListener {
            navigateToFragment(R.id.financingFragment)
        }
        binding.navCommentsFragment.setOnClickListener {
            navigateToFragment(R.id.commentsFragment)
        }
    }

    private fun navigateToFragment(fragmentId: Int) {
        navController.navigate(fragmentId)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun setupBackIcon() {
        binding.backIcon.setOnClickListener {
            onSupportNavigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}
