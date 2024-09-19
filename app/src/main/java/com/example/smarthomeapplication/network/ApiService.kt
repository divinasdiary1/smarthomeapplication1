package com.example.smarthomeapplication.network

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.http.Header

// Request data class
data class LoginRequest(
    val username: String,
    val password: String
)

// Response data class
data class LoginResponse(
    val keypass: String
)

interface AuthApi {

    @POST("/ort/auth")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    // Dashboard API to fetch the list of dishes using the keypass as a header
    @GET("/dashboard/food") // keypass - food
    fun getDashboardData(@Header("Authorization") keypass: String): Call<List<Dish>>
}