package com.example.smarthomeapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthomeapplication.R
import com.example.smarthomeapplication.network.DashboardResponse
import com.example.smarthomeapplication.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Activity that displays the dashboard data
class DashboardActivity : AppCompatActivity() {

    // RecyclerView to display the list of dishes
    private lateinit var recyclerView: RecyclerView
    // Adapter to bind the dishes data to the RecyclerView
    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize the RecyclerView and set its layout to vertical
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve the keypass (authentication token) from the intent
        val keypass = intent.getStringExtra("KEYPASS") ?: ""

        // Fetch the dashboard data using the keypass
        fetchDashboardData(keypass)
    }

    // Method to fetch data from the API
        fun fetchDashboardData(keypass: String) {
        // Make a network call using Retrofit to get the dashboard data
        RetrofitInstance.apiService.getDashboardData("Bearer $keypass").enqueue(object : Callback<DashboardResponse> {

            // Handle the API response
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val dishes = response.body()?.entities // Get the list of dishes
                    if (dishes != null) {
                        // Set the adapter with the fetched data and handle item click
                        dashboardAdapter = DashboardAdapter(dishes) { selectedDish ->
                            // On dish selection, navigate to HomeActivity with the selected dish
                            val intent = Intent(this@DashboardActivity, HomeActivity::class.java)
                            intent.putExtra("SELECTED_DISH", selectedDish)
                            startActivity(intent)
                        }
                        recyclerView.adapter = dashboardAdapter
                    }
                } else {
                    // Show a toast message if the data fetching failed
                    Toast.makeText(this@DashboardActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            // Handle the failure of the API call
            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                // Show a toast message if an error occurred
                Toast.makeText(this@DashboardActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
