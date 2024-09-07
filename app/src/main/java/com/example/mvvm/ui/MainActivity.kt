package com.example.mvvm.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.ui.view.ProductAdapter
import com.example.mvvm.ui.viewmodel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddProductActivity::class.java)
            startActivity(intent)
        }

        val productViewModel = ProductViewModel(application)
        productViewModel.loadData()?.observe(this) { value ->
            if (value != null) {
                productAdapter = ProductAdapter(this, value)
                recyclerView.adapter = productAdapter
            } else {
                Toast.makeText(this@MainActivity, "2", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
