package com.mokhaled.kotlin_coroutine_api_example.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit() :Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiServes: ApiService = getRetrofit().create(ApiService::class.java)
}