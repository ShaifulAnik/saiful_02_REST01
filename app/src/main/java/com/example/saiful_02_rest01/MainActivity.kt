package com.example.saiful_02_rest01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saiful_02_rest01.ui.ProductActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // অ্যাপ ওপেন হলেই ProductActivity-তে চলে যাবে
        startActivity(Intent(this, ProductActivity::class.java))
        finish()
    }
}