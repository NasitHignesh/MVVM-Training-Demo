package com.example.mvvm.ui


import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.R
import com.example.mvvm.api.product.model.AddProduct
import com.example.mvvm.api.product.model.ProductRating
import com.example.mvvm.ui.viewmodel.ProductViewModel

class AddProductActivity : AppCompatActivity() {

    private lateinit var addProductButton: Button
    private lateinit var editTextTitle: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextRate: EditText
    private lateinit var editTextCount: EditText
    private lateinit var image: ImageView
    private lateinit var spinner: Spinner
    private var category: String = "hello"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        addProductButton = findViewById(R.id.addProductButton)
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextPrice = findViewById(R.id.editTextPrice)
        editTextDescription = findViewById(R.id.editTextDescription)
        editTextRate = findViewById(R.id.editTextRate)
        editTextCount = findViewById(R.id.editTextCount)
        image = findViewById(R.id.demoImages)
        spinner = findViewById(R.id.spinner)

        setSpinner()

        addProductButton.setOnClickListener {
            addPro()
        }

    }

    private fun addPro() {
        val rate = editTextRate.text.toString().toDouble()
        val count = editTextCount.text.toString().toInt()
        val price = editTextPrice.text.toString().toDoubleOrNull()

        // created object
        val product = price?.let {
            AddProduct(
                category = category,
                description = editTextDescription.text.toString(),
                image = image.toString(),
                price = it,
                title = editTextTitle.text.toString(),
                rating = ProductRating(rate = rate, count = count)
            )
        }

        if (product != null) {
            val productViewModel = ProductViewModel(application = Application())
            productViewModel.addProduct(product)
        }
    }

    private fun setSpinner() {
        val adapter = ArrayAdapter.createFromResource(this@AddProductActivity, R.array.spinner_list, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
                // this data variable is selected item data
                val data = adapterView?.getItemAtPosition(i).toString()
                category = data
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                // Handle nothing selected event
            }
        }
    }
}