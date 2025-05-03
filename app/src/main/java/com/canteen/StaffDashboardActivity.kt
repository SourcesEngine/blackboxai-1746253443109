package com.canteen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.canteen.databinding.ActivityStaffDashboardBinding
import com.canteen.fragments.StaffOrdersFragment
import com.canteen.fragments.StaffProfileFragment
import com.canteen.models.User

class StaffDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStaffDashboardBinding
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = intent.getSerializableExtra("USER") as User

        setupBottomNavigation()
        loadFragment(StaffOrdersFragment())
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_orders -> loadFragment(StaffOrdersFragment())
                R.id.nav_profile -> loadFragment(StaffProfileFragment())
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