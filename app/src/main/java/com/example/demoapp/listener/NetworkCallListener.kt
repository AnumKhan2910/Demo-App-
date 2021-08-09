package com.example.demoapp.listener

import retrofit2.Response

interface NetworkCallListener {
    fun onSuccess(response: Response<*>?)
    fun onFailure()
}