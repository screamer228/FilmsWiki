package lilianisoft.test_task.filmswiki.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import lilianisoft.test_task.filmswiki.R
import lilianisoft.test_task.filmswiki.app.App
import lilianisoft.test_task.filmswiki.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.injectHostActivity(this)

        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}