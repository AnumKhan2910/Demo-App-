package com.example.demoapp.di.modules


import com.example.demoapp.constants.AppConstants
import com.example.demoapp.data.remote.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val newRequest : Request = chain.request().newBuilder()
                    .addHeader(AppConstants.HEADER_AUTHORIZATION, AppConstants.AUTHORIZATION)
                    .addHeader(AppConstants.HEADER_DEVICE_TYPE, AppConstants.DEVICE_TYPE)
                    .addHeader(AppConstants.HEADER_OS_VERSION, AppConstants.OS_VERSION)
                    .addHeader(AppConstants.HEADER_APP_VERSION, AppConstants.APP_VERSION)
                    .build()
                chain.proceed(newRequest)
            })
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesWebService(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}