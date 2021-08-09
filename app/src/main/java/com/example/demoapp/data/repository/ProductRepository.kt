package com.example.demoapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.demoapp.data.remote.WebServices
import com.example.demoapp.dataSource.ProductDataSource
import com.example.demoapp.entity.Product
import com.example.demoapp.listener.NetworkCallListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ProductRepository @Inject constructor(private var webServices: WebServices) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    fun getProductList(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),

            pagingSourceFactory = {
                ProductDataSource(webServices)
            }

        ).flow
    }


    fun getProductDetails(productId : Int, networkCallListener: NetworkCallListener) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val response = webServices.fetchProductDetails(productId)
                if (response.isSuccessful) {
                    networkCallListener.onSuccess(response)
                } else {
                    networkCallListener.onFailure()
                }
            }
        } catch (e : Exception){
            networkCallListener.onFailure()
        }
    }

}