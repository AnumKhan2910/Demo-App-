package com.example.demoapp.response

import com.example.demoapp.entity.Product
import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse (
    @SerializedName("status")
    var status: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("body")
    var data: Product
    )