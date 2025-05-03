package com.canteen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.canteen.databinding.ActivityAdminDashboardBinding
import com.canteen.fragments.AdminMenuFragment
import com.canteen.fragments.AdminReportsFragment
import com.canteen.fragments.AdminUsersFragment
import com.canteen.models.User

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminDashboardBinding
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = intent.getSerializableExtra("USER") as User

        setupBottomNavigation()
        loadFragment(AdminMenuFragment())
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_menu -> loadFragment(AdminMenuFragment())
                R.id.nav_reports -> loadFragment(AdminReportsFragment())
                R.id.nav_users -> loadFragment(AdminUsersFragment())
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