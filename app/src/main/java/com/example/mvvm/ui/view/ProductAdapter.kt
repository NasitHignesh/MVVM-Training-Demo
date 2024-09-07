package com.example.mvvm.ui.view

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.api.product.model.ProductDataItem
import com.example.mvvm.ui.AddProductActivity
import com.example.mvvm.ui.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso

class ProductAdapter(private val context: Context, private val ProductList: List<ProductDataItem>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var category: TextView = itemView.findViewById(R.id.Category)
        var title: TextView = itemView.findViewById(R.id.title)
        var ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        var totalRating: TextView = itemView.findViewById(R.id.totalRating)
        var price: TextView = itemView.findViewById(R.id.price)
        var id: TextView = itemView.findViewById(R.id.id)
        var toolbar: androidx.appcompat.widget.Toolbar = itemView.findViewById(R.id.toolbar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currItem = ProductList[position]
        val rate: Double = currItem.rating.rate * 2

        Picasso.get().load(currItem.image).into(holder.imageView)
        holder.category.text = "Category : ${currItem.category}"
        holder.title.text = currItem.title
        holder.totalRating.text = "Rating : ${currItem.rating.count}"
        holder.price.text = "Price : $${currItem.price}"
        holder.id.text = "Id : ${currItem.id} "
        holder.ratingBar.setProgress(rate.toInt(), true)

        holder.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete -> {
                    var productViewModel = ProductViewModel(application = Application())
                    productViewModel.delProduct(currItem.id)
                    true
                }
                R.id.edit -> {
                    val intent = Intent(context, AddProductActivity::class.java)
                    context.startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}