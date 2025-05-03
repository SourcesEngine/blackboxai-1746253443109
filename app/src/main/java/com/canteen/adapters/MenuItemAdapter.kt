package com.canteen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.canteen.R
import com.canteen.models.MenuItem

class MenuItemAdapter(
    private var items: List<MenuItem>,
    private val isAdmin: Boolean,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>() {

    inner class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.itemPriceTextView)
        private val stockTextView: TextView = itemView.findViewById(R.id.itemStockTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        private val editButton: View = itemView.findViewById(R.id.editButton)
        private val deleteButton: View = itemView.findViewById(R.id.deleteButton)

        fun bind(item: MenuItem) {
            nameTextView.text = item.name
            priceTextView.text = "$${item.price}"
            stockTextView.text = "Stock: ${item.stock}"

            item.imageUrl?.let { url ->
                Glide.with(itemView.context)
                    .load(url)
                    .placeholder(R.drawable.ic_food_placeholder)
                    .into(imageView)
            } ?: run {
                imageView.setImageResource(R.drawable.ic_food_placeholder)
            }

            if (isAdmin) {
                editButton.visibility = View.VISIBLE
                deleteButton.visibility = View.VISIBLE
                editButton.setOnClickListener { onItemClick(item.copy()) }
                deleteButton.setOnClickListener { onItemClick(item) }
            } else {
                editButton.visibility = View.GONE
                deleteButton.visibility = View.GONE
                itemView.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return MenuItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<MenuItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}