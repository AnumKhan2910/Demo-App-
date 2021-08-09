package com.example.demoapp.data.remote

import com.example.demoapp.response.ProductDetailsResponse
import com.example.demoapp.response.ProductListBaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {

    @GET("/v1/products")
    suspend fun fetchProducts(@Query("category__id") id: Int = 10,
                              @Query("page") page : Int) : Response<ProductListBaseResponse>


    @GET("/v1/product/{productId}")
    suspend fun fetchProductDetails(@Path("productId") id : Int): Response<ProductDetailsResponse>

}