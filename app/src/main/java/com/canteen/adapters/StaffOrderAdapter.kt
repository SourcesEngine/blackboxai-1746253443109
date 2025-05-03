package com.canteen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.canteen.R
import com.canteen.models.Order

class StaffOrderAdapter(
    private var orders: List<Order>,
    private val onOrderAction: (Order, String) -> Unit
) : RecyclerView.Adapter<StaffOrderAdapter.StaffOrderViewHolder>() {

    inner class StaffOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.orderIdTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.orderDateTextView)
        private val totalTextView: TextView = itemView.findViewById(R.id.orderTotalTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.orderStatusTextView)
        private val paymentTextView: TextView = itemView.findViewById(R.id.orderPaymentTextView)
        private val completeButton: Button = itemView.findViewById(R.id.completeButton)
        private val cancelButton: Button = itemView.findViewById(R.id.cancelButton)

        fun bind(order: Order) {
            idTextView.text = "Order #${order.id}"
            dateTextView.text = order.createdAt
            totalTextView.text = "$${order.totalPrice}"
            statusTextView.text = when(order.status) {
                "pending" -> "Pending"
                "completed" -> "Completed"
                "cancelled" -> "Cancelled"
                else -> order.status
            }
            paymentTextView.text = when(order.paymentMethod) {
                "cash" -> "Cash on Pickup"
                "e-wallet" -> "E-Wallet"
                else -> order.paymentMethod
            }

            completeButton.visibility = if (order.status == "pending") View.VISIBLE else View.GONE
            cancelButton.visibility = if (order.status == "pending") View.VISIBLE else View.GONE

            completeButton.setOnClickListener { onOrderAction(order, "complete") }
            cancelButton.setOnClickListener { onOrderAction(order, "cancel") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffOrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staff_order, parent, false)
        return StaffOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaffOrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    fun updateItems(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }

    fun updateOrderStatus(orderId: Int, newStatus: String) {
        orders = orders.map { 
            if (it.id == orderId) it.copy(status = newStatus) else it 
        }
        notifyDataSetChanged()
    }
}