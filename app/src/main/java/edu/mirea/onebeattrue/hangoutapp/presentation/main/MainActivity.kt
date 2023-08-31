package edu.mirea.onebeattrue.hangoutapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import edu.mirea.onebeattrue.hangoutapp.R
import edu.mirea.onebeattrue.hangoutapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        setupWithNavController(binding.bottomNavigationView, navController)

        navController.addOnDestinationChangedListener {
                controller: NavController?, destination: NavDestination, arguments: Bundle? ->
            if (destination.id in DESTINATIONS_WITHOUT_BOTTOM_MENU) {
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private val DESTINATIONS_WITHOUT_BOTTOM_MENU = listOf(
            R.id.loginFragment,
            R.id.registerFragment
        )
    }
}