package com.example.demoapp.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demoapp.data.remote.WebServices
import com.example.demoapp.entity.Product
import com.example.demoapp.response.ProductListBaseResponse

class ProductDataSource (private val api: WebServices) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.fetchProducts(page = nextPageNumber)
            val baseResponse = response.body() as ProductListBaseResponse
            LoadResult.Page(
                data = baseResponse.data.productList,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < baseResponse.data.lastPage) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}