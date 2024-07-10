package lilianisoft.test_task.filmswiki.host

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import lilianisoft.test_task.filmswiki.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import lilianisoft.test_task.filmswiki.data.RetrofitInstance
import lilianisoft.test_task.filmswiki.databinding.ActivityHostBinding
import lilianisoft.test_task.filmswiki.model.Movie
import lilianisoft.test_task.filmswiki.model.MovieResponse

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

const val API_KEY = "d483acb9b6f697c31f603b7e6e2e2722"