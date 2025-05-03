package com.canteen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.canteen.databinding.ActivityRegisterBinding
import com.canteen.models.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DatabaseHelper(this)

        // Setup role spinner
        val roles = resources.getStringArray(R.array.roles_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.roleSpinner.adapter = adapter

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()
            val fullName = binding.fullNameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val role = when (binding.roleSpinner.selectedItemPosition) {
                0 -> "student"
                1 -> "staff"
                2 -> "admin"
                else -> "student"
            }

            if (validateInput(username, password, confirmPassword, fullName, email)) {
                if (registerUser(username, password, fullName, email, role)) {
                    Toast.makeText(this, "Registration successful! 500.00 credited to your wallet", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed. Username may be taken", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateInput(
        username: String,
        password: String,
        confirmPassword: String,
        fullName: String,
        email: String
    ): Boolean {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun registerUser(
        username: String,
        password: String,
        fullName: String,
        email: String,
        role: String
    ): Boolean {
        val db = dbHelper.writableDatabase
        val values = android.content.ContentValues().apply {
            put("username", username)
            put("password", password)
            put("role", role)
            put("wallet_balance", if (role == "student") 500.00 else 0.00)
            put("full_name", fullName)
            put("email", email)
        }

        return try {
            db.insert("users", null, values) != -1L
        } catch (e: Exception) {
            false
        }
    }
}