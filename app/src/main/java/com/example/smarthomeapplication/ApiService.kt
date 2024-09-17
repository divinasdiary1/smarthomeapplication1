package com.example.smarthomeapplication

import android.telecom.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Creating an interface to determine API endpoints

// Data class for Login request body
data class LoginRequest(
    var username: String,
    var password: String
)

// Data class for Login response body
data class LoginResponse(
    var keypass: String
)

// Defining VU Login Endpoint
interface ApiService {
    @POST("/footscray/auth")
    fun login(@Body loginRequest: String): Call<LoginResponse>
}