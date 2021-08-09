package com.example.demoapp.entity

import com.google.gson.annotations.SerializedName

data class Info(

    @SerializedName("name")
    var name : String,

    @SerializedName("text")
    var description : String
)
