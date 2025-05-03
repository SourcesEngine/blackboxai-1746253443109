package com.canteen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.canteen.databinding.ActivityLoginBinding
import com.canteen.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(username, password)
        }

        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser(username: String, password: String) {
        val call = ApiClient.apiService.login(username, password)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.user
                    user?.let {
                        when (it.role) {
                            "admin" -> {
                                val intent = Intent(this@LoginActivity, AdminDashboardActivity::class.java)
                                intent.putExtra("USER", it)
                                startActivity(intent)
                            }
                            "staff" -> {
                                val intent = Intent(this@LoginActivity, StaffDashboardActivity::class.java)
                                intent.putExtra("USER", it)
                                startActivity(intent)
                            }
                            else -> {
                                val intent = Intent(this@LoginActivity, StudentDashboardActivity::class.java)
                                intent.putExtra("USER", it)
                                startActivity(intent)
                            }
                        }
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}