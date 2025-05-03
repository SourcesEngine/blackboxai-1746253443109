package com.canteen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canteen.R
import com.canteen.databinding.FragmentStaffProfileBinding
import com.canteen.models.User

class StaffProfileFragment : Fragment() {
    private var _binding: FragmentStaffProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentUser: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStaffProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentUser = arguments?.getSerializable("USER") as User
        setupViews()
    }

    private fun setupViews() {
        binding.fullNameTextView.text = currentUser.fullName ?: currentUser.username
        binding.emailTextView.text = currentUser.email
        binding.roleTextView.text = getString(R.string.staff_role)
        
        binding.changePasswordButton.setOnClickListener {
            // TODO: Implement password change
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}