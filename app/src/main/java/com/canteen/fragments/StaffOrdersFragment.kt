package com.canteen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.canteen.R
import com.canteen.adapters.StaffOrderAdapter
import com.canteen.databinding.FragmentStaffOrdersBinding
import com.canteen.models.Order

class StaffOrdersFragment : Fragment() {
    private var _binding: FragmentStaffOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var orderAdapter: StaffOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStaffOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadOrders()
    }

    private fun setupRecyclerView() {
        orderAdapter = StaffOrderAdapter(emptyList()) { order, action ->
            when (action) {
                "complete" -> completeOrder(order)
                "cancel" -> cancelOrder(order)
            }
        }
        
        binding.ordersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
    }

    private fun loadOrders() {
        // TODO: Load orders from database
        val dummyOrders = listOf(
            Order(1, 1, 18.97, "pending", "cash", "2023-05-15"),
            Order(2, 1, 12.98, "pending", "e-wallet", "2023-05-10")
        )
        orderAdapter.updateItems(dummyOrders)
    }

    private fun completeOrder(order: Order) {
        // TODO: Mark order as completed in database
        orderAdapter.updateOrderStatus(order.id, "completed")
    }

    private fun cancelOrder(order: Order) {
        // TODO: Mark order as cancelled in database
        orderAdapter.updateOrderStatus(order.id, "cancelled")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}