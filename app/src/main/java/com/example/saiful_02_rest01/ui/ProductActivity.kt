package com.example.saiful_02_rest01.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saiful_02_rest01.R
import com.example.saiful_02_rest01.adapter.ProductAdapter
import com.example.saiful_02_rest01.databinding.ActivityProductBinding
import com.example.saiful_02_rest01.model.Product
import com.example.saiful_02_rest01.network.ApiService
import com.example.saiful_02_rest01.network.NetworkUtils
import org.koin.android.ext.android.inject // Koin inject import
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutError: LinearLayout
    private lateinit var btnRetry: Button

    // Koin-এর মাধ্যমে ApiService Inject করা হলো
    private val apiService: ApiService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        // ১. এই লাইনটি অবশ্যই সবার উপরে থাকতে হবে
        super.onCreate(savedInstanceState)

        // ২. তারপর View Binding inflate করুন
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ৩. বাকি সব কাজ নিচে
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)

        fetchProductData()

        binding.btnRetry.setOnClickListener {
            fetchProductData()
        }
    }

    private fun fetchProductData() {
        if (!NetworkUtils.isInternetAvailable(this)) {
            showError("No Internet Connection! Please check your network.")
            return
        }

        showLoading()

        apiService.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful && response.body() != null) {
                    showData(response.body()!!)
                } else {
                    showError("Failed to fetch data from server!")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                showError("API Request Failed: ${t.localizedMessage}")
            }
        })
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerViewProducts.visibility = View.GONE
        binding.layoutError.visibility = View.GONE
    }

    private fun showData(products: List<Product>) {
        binding.progressBar.visibility = View.GONE
        binding.layoutError.visibility = View.GONE
        binding.recyclerViewProducts.visibility = View.VISIBLE
        binding.recyclerViewProducts.adapter = ProductAdapter(products)
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerViewProducts.visibility = View.GONE
        binding.layoutError.visibility = View.VISIBLE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}