package com.example.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.databinding.ItemProductBinding
import com.example.demoapp.entity.Product
import com.example.demoapp.listener.ItemClickListener

class ProductListAdapter(private var context: Context,
                         private var itemClickListener: ItemClickListener) :
    PagingDataAdapter<Product, RecyclerView.ViewHolder>(DiffCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val productBinding: ItemProductBinding = ItemProductBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ProductViewHolder(productBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder){
            if (getItem(position) != null) {
                holder.title.text = getItem(position)?.name
                holder.subTitle.text = getItem(position)?.vendorName
                holder.price.text = String.format(context.resources.getString(R.string.text_amount), getItem(position)?.salePrice)

                Glide.with(context)
                    .load(getItem(position)?.thumbnail)
                    .placeholder(R.color.grey)
                    .into(holder.imageView)

                holder.view.setOnClickListener {
                    getItem(position)?.let { it1 -> itemClickListener.onItemClicked(it1) }
                }

            }
        }
    }

    class ProductViewHolder(productBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(productBinding.root) {
        val view : ConstraintLayout = productBinding.productView
        val title: TextView = productBinding.titleText
        val subTitle : TextView = productBinding.subTitleText
        val price: TextView = productBinding.priceText
        val imageView : ImageView = productBinding.imageView
    }


    class DiffCallBack : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
}