package com.canteen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.canteen.R
import com.canteen.models.Order

class OrderAdapter(
    private var orders: List<Order>,
    private val onOrderClick: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.orderIdTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.orderDateTextView)
        private val totalTextView: TextView = itemView.findViewById(R.id.orderTotalTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.orderStatusTextView)
        private val paymentTextView: TextView = itemView.findViewById(R.id.orderPaymentTextView)

        fun bind(order: Order) {
            idTextView.text = "Order #${order.id}"
            dateTextView.text = order.createdAt
            totalTextView.text = "$${order.totalPrice}"
            statusTextView.text = when(order.status) {
                "pending" -> "Processing"
                "completed" -> "Completed"
                "cancelled" -> "Cancelled"
                else -> order.status
            }
            paymentTextView.text = when(order.paymentMethod) {
                "cash" -> "Cash on Pickup"
                "e-wallet" -> "E-Wallet"
                else -> order.paymentMethod
            }

            itemView.setOnClickListener { onOrderClick(order) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    fun updateItems(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }
}