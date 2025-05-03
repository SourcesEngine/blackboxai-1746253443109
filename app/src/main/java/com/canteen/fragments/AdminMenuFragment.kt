package com.canteen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.canteen.R
import com.canteen.adapters.MenuItemAdapter
import com.canteen.databinding.FragmentAdminMenuBinding
import com.canteen.models.MenuItem

class AdminMenuFragment : Fragment() {
    private var _binding: FragmentAdminMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuAdapter: MenuItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMenuItems()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        menuAdapter = MenuItemAdapter(emptyList(), true) { menuItem ->
            // Handle item click (edit/delete)
        }
        
        binding.menuRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuAdapter
        }
    }

    private fun loadMenuItems() {
        // TODO: Load menu items from database
        val dummyItems = listOf(
            MenuItem(1, "Burger", "Delicious beef burger", 5.99, 10, null, true, "Main"),
            MenuItem(2, "Pizza", "Cheese pizza", 7.99, 5, null, true, "Main"),
            MenuItem(3, "Salad", "Fresh garden salad", 4.99, 8, null, true, "Starter")
        )
        menuAdapter.updateItems(dummyItems)
    }

    private fun setupClickListeners() {
        binding.addItemButton.setOnClickListener {
            // TODO: Open add item dialog
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}