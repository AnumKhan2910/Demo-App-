package com.example.demoapp.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.demoapp.entity.Product
import com.example.demoapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject
      constructor(private var productRepository: ProductRepository) :
      ViewModel() {

    fun getProductList() : Flow<PagingData<Product>> {
       return productRepository.getProductList()
           .map { pagingData -> pagingData.map { it } }
           .cachedIn(viewModelScope)
    }
}