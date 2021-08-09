package com.example.demoapp.response

import com.google.gson.annotations.SerializedName

data class ProductListBaseResponse(
    @SerializedName("status")
    var status: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("body")
    var data: ProductListResponse
)

