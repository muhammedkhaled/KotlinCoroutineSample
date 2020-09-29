package com.mokhaled.kotlin_coroutine_api_example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.example.coroutines.utils.Resource
import com.mokhaled.kotlin_coroutine_api_example.data.api.RetrofitBuilder
import com.mokhaled.kotlin_coroutine_api_example.data.model.ApiPostsModel
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel : ViewModel() {

    private val posts = MutableLiveData<Resource<List<ApiPostsModel>>>()
    private val apiService = RetrofitBuilder.apiServes
    val postsLiveData: LiveData<Resource<List<ApiPostsModel>>>
        get() = posts

    init {
        fetchPosts()
    }

    private fun fetchPosts(){
        viewModelScope.launch {
            posts.postValue(Resource.loading(null))
            try {
                val postsFromApi = apiService.getPosts()
                posts.postValue(Resource.success(postsFromApi))
            } catch (e: Exception){
                posts.postValue(Resource.error(e.toString(), null))
            }
        }
    }
}