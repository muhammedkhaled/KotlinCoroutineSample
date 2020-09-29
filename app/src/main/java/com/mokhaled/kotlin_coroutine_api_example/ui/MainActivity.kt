package com.mokhaled.kotlin_coroutine_api_example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.example.coroutines.utils.Status
import com.mokhaled.kotlin_coroutine_api_example.R
import com.mokhaled.kotlin_coroutine_api_example.data.apapter.PostsAdapter
import com.mokhaled.kotlin_coroutine_api_example.data.model.ApiPostsModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUi()
        setViewModel()
        setObserver()
    }

    private fun setUi() {
        list_rv.layoutManager = LinearLayoutManager(this)
        adapter = PostsAdapter(arrayListOf())
        list_rv.adapter = adapter
    }

    private fun setObserver() {
        viewModel.postsLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { posts -> renderPosts(posts) }
                    list_rv.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    list_rv.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    Log.d("MainActivity", "setObserver: " + it.message)
                }

            }
        })
    }

    private fun renderPosts(posts: List<ApiPostsModel>) {
        adapter.addData(posts)
        adapter.notifyDataSetChanged()
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }
}