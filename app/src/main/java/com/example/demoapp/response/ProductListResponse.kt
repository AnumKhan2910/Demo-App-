package com.example.demoapp.response

import com.example.demoapp.entity.Product
import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("data")
    var productList : List<Product>,

    @SerializedName("current_page")
    var currentPage : Int,

    @SerializedName("last_page")
    var lastPage : Int
)