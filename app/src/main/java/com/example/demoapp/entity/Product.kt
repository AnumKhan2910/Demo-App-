package com.example.demoapp.entity

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id")
    var id : Int,

    @SerializedName("name")
    var name : String,

    @SerializedName("final_price_sale")
    var salePrice : String,

    @SerializedName("thumbnail_url")
    var thumbnail : String,

    @SerializedName("vendor_name")
    var vendorName : String,

    @SerializedName("brand_name")
    var brandName : String,

    @SerializedName("images")
    var images : List<String>,

    @SerializedName("info")
    var info : List<Info>
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other.javaClass !== this.javaClass) {
            return false
        }

        return other is Product && other?.id == this.id
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + salePrice.hashCode()
        result = 31 * result + thumbnail.hashCode()
        result = 31 * result + vendorName.hashCode()
        result = 31 * result + brandName.hashCode()
        result = 31 * result + images.hashCode()
        result = 31 * result + info.hashCode()
        return result
    }
}
