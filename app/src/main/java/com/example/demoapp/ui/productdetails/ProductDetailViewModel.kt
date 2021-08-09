package com.example.demoapp.ui.productdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapp.data.repository.ProductRepository
import com.example.demoapp.entity.Product
import com.example.demoapp.listener.NetworkCallListener
import com.example.demoapp.response.ProductDetailsResponse
import com.example.demoapp.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private var productRepository: ProductRepository) : ViewModel() {

    private var uiState = MutableLiveData<UIState>()
    var uiStateLiveData: LiveData<UIState> = uiState

    private var productDetail = MutableLiveData<Product>()
    var productDetailLiveData: LiveData<Product> = productDetail


    fun fetchProductDetails(productId : Int){
        uiState.value = UIState.LoadingState
        productRepository.getProductDetails(productId, object : NetworkCallListener {
            override fun onSuccess(response: Response<*>?) {
                uiState.postValue(UIState.DataState)
                val res = response?.body() as ProductDetailsResponse
                productDetail.postValue(res.data)
            }

            override fun onFailure() {
                uiState.postValue(UIState.ErrorState)
            }
        })
    }

}