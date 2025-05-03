package com.canteen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.canteen.R
import com.canteen.adapters.OrderAdapter
import com.canteen.databinding.FragmentStudentOrdersBinding
import com.canteen.models.Order

class StudentOrdersFragment : Fragment() {
    private var _binding: FragmentStudentOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadOrders()
    }

    private fun setupRecyclerView() {
        orderAdapter = OrderAdapter(emptyList()) { order ->
            // Handle order click (view details)
        }
        
        binding.ordersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
    }

    private fun loadOrders() {
        // TODO: Load orders from database for current user
        val dummyOrders = listOf(
            Order(1, 1, 18.97, "completed", "cash", "2023-05-15"),
            Order(2, 1, 12.98, "completed", "e-wallet", "2023-05-10")
        )
        orderAdapter.updateItems(dummyOrders)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}