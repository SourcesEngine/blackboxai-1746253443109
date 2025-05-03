package com.canteen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.canteen.R
import com.canteen.models.User

class UserAdapter(
    private var users: List<User>,
    private val onUserClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.userEmailTextView)
        private val roleTextView: TextView = itemView.findViewById(R.id.userRoleTextView)
        private val walletTextView: TextView = itemView.findViewById(R.id.userWalletTextView)
        private val editButton: ImageView = itemView.findViewById(R.id.editButton)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

        fun bind(user: User) {
            nameTextView.text = user.fullName ?: user.username
            emailTextView.text = user.email
            roleTextView.text = when(user.role) {
                "admin" -> "Administrator"
                "staff" -> "Canteen Staff"
                "student" -> "Student"
                else -> user.role
            }
            
            if (user.role == "student") {
                walletTextView.text = "Wallet: ${user.walletBalance}"
                walletTextView.visibility = View.VISIBLE
            } else {
                walletTextView.visibility = View.GONE
            }

            editButton.setOnClickListener { onUserClick(user.copy()) }
            deleteButton.setOnClickListener { onUserClick(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun updateItems(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }
}