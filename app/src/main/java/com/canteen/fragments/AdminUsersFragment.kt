package com.canteen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.canteen.R
import com.canteen.adapters.UserAdapter
import com.canteen.databinding.FragmentAdminUsersBinding
import com.canteen.models.User

class AdminUsersFragment : Fragment() {
    private var _binding: FragmentAdminUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadUsers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(emptyList()) { user ->
            // Handle user click (edit/delete)
        }
        
        binding.usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }
    }

    private fun loadUsers() {
        // TODO: Load users from database
        val dummyUsers = listOf(
            User(1, "admin", "admin123", "admin", 0.0, "Admin User", "admin@school.edu"),
            User(2, "staff1", "staff123", "staff", 0.0, "Staff Member", "staff@school.edu"),
            User(3, "student1", "student123", "student", 500.0, "Student One", "student1@school.edu")
        )
        userAdapter.updateItems(dummyUsers)
    }

    private fun setupClickListeners() {
        binding.addStaffButton.setOnClickListener {
            // TODO: Open add staff dialog
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}