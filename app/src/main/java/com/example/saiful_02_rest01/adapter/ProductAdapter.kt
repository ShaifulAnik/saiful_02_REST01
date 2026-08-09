package com.example.saiful_02_rest01.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saiful_02_rest01.databinding.ItemProductBinding
import com.example.saiful_02_rest01.model.Product

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // View Binding ব্যবহার করে সরাসরি ভিউ অ্যাক্সেস
        holder.binding.tvTitle.text = product.title
        holder.binding.tvPrice.text = "$${product.price}"

        // Glide Image Loading
        Glide.with(holder.itemView.context)
            .load(product.image)
            .placeholder(android.R.drawable.stat_sys_download)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.binding.imgProduct)
    }

    override fun getItemCount(): Int = productList.size
}