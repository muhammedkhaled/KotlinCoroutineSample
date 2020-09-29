package com.mokhaled.kotlin_coroutine_api_example.data.api

import com.mokhaled.kotlin_coroutine_api_example.data.model.ApiPostsModel
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<ApiPostsModel>

}