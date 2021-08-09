package com.example.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater

import android.view.View

import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R


class ProductImageAdapter(private var context: Context, private var items: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            Glide.with(context)
                .load(items[position])
                .placeholder(R.color.grey)
                .into(holder.imageView)

            holder.imageNumber.text = String.format(context.getString(R.string.image_number)
                ,(position+1).toString(),itemCount.toString())
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView : ImageView = view.findViewById(R.id.imageView)
        val imageNumber : TextView = view.findViewById(R.id.imageNumber)
    }


}