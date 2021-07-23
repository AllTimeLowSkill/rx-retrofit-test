package com.example.rx.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rx.R
import com.example.rx.models.DataResponse
import com.example.rx.services.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PostAdapter(private val postList: List<DataResponse>) :
    RecyclerView.Adapter<PostAdapter.PostHolder>() {
    class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val compositeDisposable = CompositeDisposable()
        val postId: TextView = view.findViewById(R.id.post_id)
        val postTitle: TextView = view.findViewById(R.id.post_title)
        val postBody: TextView = view.findViewById(R.id.post_body)

        init {
            itemView.setOnClickListener {
                val context = it.context
                compositeDisposable.add(
                    ApiService.apiService.getPost(postId.text.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ subscribe ->

                            Toast.makeText(context, subscribe.title, Toast.LENGTH_SHORT).show()
                        }, { err ->
                            Log.v("TEST_CLICK", "$err")
                        })
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_post, parent, false)
        return PostHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val listItem = postList[position]

        holder.postTitle.text = listItem.title
        holder.postBody.text = listItem.body
        holder.postId.text = listItem.id.toString()
    }

    override fun getItemCount(): Int = postList.size
}