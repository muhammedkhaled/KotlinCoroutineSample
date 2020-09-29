package com.mokhaled.kotlin_coroutine_api_example.data.apapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mokhaled.kotlin_coroutine_api_example.R
import com.mokhaled.kotlin_coroutine_api_example.data.model.ApiPostsModel
import kotlinx.android.synthetic.main.item.view.*

class PostsAdapter(private val posts: ArrayList<ApiPostsModel>) :
    RecyclerView.Adapter<PostsAdapter.DataVh>() {

    fun addData(list: List<ApiPostsModel>){
        posts.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVh =
        DataVh(LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false))

    override fun onBindViewHolder(holder: DataVh, position: Int) =
        holder.bind(posts[position])

    override fun getItemCount(): Int = posts.size


    class DataVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(posts: ApiPostsModel) {
            itemView.title_tv.text = posts.title
            itemView.body_tv.text = posts.body
        }
    }
}

