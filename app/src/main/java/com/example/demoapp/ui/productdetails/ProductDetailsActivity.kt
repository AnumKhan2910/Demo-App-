package com.example.demoapp.ui.productdetails

import android.app.ProgressDialog
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.R
import com.example.demoapp.adapter.ProductImageAdapter
import com.example.demoapp.constants.AppConstants
import com.example.demoapp.databinding.ActivityProductDetailsBinding
import com.example.demoapp.ui.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val productDetailViewModel : ProductDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setToolbar()
        fetchData()
        observeUIState()
        observeData()
    }

    private fun setToolbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.navigationIcon?.
        setColorFilter(resources.getColor(R.color.purple_700), PorterDuff.Mode.SRC_ATOP)

    }

    private fun fetchData(){
        intent.getIntExtra(AppConstants.PRODUCT_ID, 2075)
            .let { productDetailViewModel.fetchProductDetails(it) }
    }

    private fun observeData(){
        productDetailViewModel.productDetailLiveData.observe(this, {
            binding.titleText.text = it.name
            binding.subTitleText.text = it.brandName
            binding.priceText.text = String.format(resources.getString(R.string.text_amount), it.salePrice)
            binding.descriptionText.text = HtmlCompat.fromHtml(it.info[0].description,
                HtmlCompat.FROM_HTML_MODE_LEGACY)

            val adapter = ProductImageAdapter(this, it.images)
            binding.listView.setHasFixedSize(true)
            binding.listView.adapter = adapter
            binding.listView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        })
    }

    private fun observeUIState() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.text_loading))

        productDetailViewModel.uiStateLiveData.observe(this, { state ->
            when(state){
                UIState.LoadingState -> {
                    progressDialog.show()
                }

                UIState.DataState -> {
                    progressDialog.hide()
                }

                UIState.ErrorState -> {
                    Toast.makeText(this, getString(R.string.text_error), Toast.LENGTH_LONG).show()
                    progressDialog.hide()
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}