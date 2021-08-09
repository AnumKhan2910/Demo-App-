package com.example.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.entity.Product
import com.example.demoapp.listener.ItemClickListener

class ProductListAdapter(private var context: Context,
                         private var itemClickListener: ItemClickListener) :
    PagingDataAdapter<Product, RecyclerView.ViewHolder>(DiffCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
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

    class ProductViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val view : ConstraintLayout = view.findViewById(R.id.product_view)
        val title: TextView = view.findViewById(R.id.titleText)
        val subTitle : TextView = view.findViewById(R.id.subTitleText)
        val price: TextView = view.findViewById(R.id.priceText)
        val imageView : ImageView = view.findViewById(R.id.imageView)
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