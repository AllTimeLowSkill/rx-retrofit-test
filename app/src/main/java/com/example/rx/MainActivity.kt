package com.example.rx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rx.adapters.PostAdapter
import com.example.rx.services.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var postList: RecyclerView
    private lateinit var postListAdapter: PostAdapter
    private lateinit var  layoutManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postList = findViewById(R.id.post_list)
        postList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        postList.layoutManager = layoutManager

        testApi()
    }

    private fun testApi() {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ApiService.apiService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    postListAdapter = PostAdapter(it)
                    postListAdapter.notifyDataSetChanged()
                    postList.adapter = postListAdapter
                }, {
                    Log.v("TEST", "$it")
                })
        )
    }
}
