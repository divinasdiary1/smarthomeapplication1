package com.example.smarthomeapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.view.WindowInsetsAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // Injecting dependency using Hilt
    @Inject
    lateinit var apiService: ApiService  // Injected by Hilt

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Calling edit text by id
        val username: EditText = findViewById(R.id.username_edit_text)
        val password: EditText = findViewById(R.id.password_edit_text)

        // Logging into Home App with Connect Home button
        val submitButton: Button = findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)// Using intent to start Home Activity class
            // Function to perform login
            fun performLogin(username: String, password: String) {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    val loginRequest = LoginRequest(username, password)
            performLogin(user, pass) // function for Login
        }
        // Handle edge insets for immersive UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
                // Call login API
                apiService.login("/footscray/auth", LoginRequest)
                    .enqueue(object : WindowInsetsAnimation.Callback<LoginResponse> {
                        fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            if (response.isSuccessful) {
                                val keypass = response.body()?.keypass
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login Success! Keypass: $keypass",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // Navigate to HomeActivity on successful login
                                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login failed: ${response.message()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(
                                this@MainActivity,
                                "Error: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            } /* else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
                    .show()
                    */
            }
    }
}