package com.example.demoapp.ui.productlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.adapter.ProductListAdapter
import com.example.demoapp.constants.AppConstants
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.entity.Product
import com.example.demoapp.listener.ItemClickListener
import com.example.demoapp.ui.productdetails.ProductDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val productViewModel : ProductViewModel by viewModels()
    private lateinit var productListAdapter : ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()
        observeData()
    }

    private fun initViews(){
        productListAdapter = ProductListAdapter(this, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = productListAdapter

        /*
         * Progress Updater
         */
        productListAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
            }
            else {
                binding.progressBar.visibility = View.GONE
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            productViewModel.getProductList().collectLatest { pagedData ->
                productListAdapter.submitData(pagedData)
            }
        }
    }

    override fun onItemClicked(product: Product) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra(AppConstants.PRODUCT_ID, product.id)
        startActivity(intent)
    }
}