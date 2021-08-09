package com.example.demoapp.listener

import com.example.demoapp.entity.Product

interface ItemClickListener {
    fun onItemClicked(product : Product)
}