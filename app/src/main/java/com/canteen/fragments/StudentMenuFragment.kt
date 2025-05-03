package com.canteen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.canteen.R
import com.canteen.adapters.MenuItemAdapter
import com.canteen.databinding.FragmentStudentMenuBinding
import com.canteen.models.MenuItem

class StudentMenuFragment : Fragment() {
    private var _binding: FragmentStudentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuAdapter: MenuItemAdapter
    private var cartItems = mutableListOf<MenuItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMenuItems()
        setupClickListeners()
        updateCartSummary()
    }

    private fun setupRecyclerView() {
        menuAdapter = MenuItemAdapter(emptyList(), false) { menuItem ->
            cartItems.add(menuItem)
            updateCartSummary()
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
        binding.viewCartButton.setOnClickListener {
            // TODO: Open cart activity
        }

        binding.filterButton.setOnClickListener {
            // TODO: Open filter dialog
        }
    }

    private fun updateCartSummary() {
        val itemCount = cartItems.size
        val total = cartItems.sumOf { it.price.toDouble() }
        
        binding.cartSummary.text = if (itemCount > 0) {
            getString(R.string.cart_summary, itemCount, total)
        } else {
            getString(R.string.cart_empty)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}