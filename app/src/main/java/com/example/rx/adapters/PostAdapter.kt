package com.example.rx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rx.R
import com.example.rx.models.DataResponse

class PostAdapter(private val postList: List<DataResponse>) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    class PostHolder(view: View): RecyclerView.ViewHolder(view) {
        val postTitle: TextView = view.findViewById(R.id.post_title)
        val postBody: TextView = view.findViewById(R.id.post_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_post, parent, false)
        return  PostHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val listItem = postList[position]

        holder.postTitle.text = listItem.title
        holder.postBody.text = listItem.body
    }

    override fun getItemCount(): Int = postList.size
}