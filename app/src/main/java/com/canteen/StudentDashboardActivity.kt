package com.canteen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.canteen.databinding.ActivityStudentDashboardBinding
import com.canteen.fragments.StudentMenuFragment
import com.canteen.fragments.StudentOrdersFragment
import com.canteen.fragments.StudentProfileFragment
import com.canteen.models.User

class StudentDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDashboardBinding
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = intent.getSerializableExtra("USER") as User

        setupBottomNavigation()
        loadFragment(StudentMenuFragment())
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_menu -> loadFragment(StudentMenuFragment())
                R.id.nav_orders -> loadFragment(StudentOrdersFragment())
                R.id.nav_profile -> loadFragment(StudentProfileFragment())
                R.id.nav_logout -> logout()
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun logout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}