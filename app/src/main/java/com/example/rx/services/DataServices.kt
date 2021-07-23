package com.example.rx.services

import com.example.rx.models.DataResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataServices {
    @GET("posts")
    fun getPosts(): Single<List<DataResponse>>

    @GET("posts/{id}")
    fun getPost(@Path("id")id: String): Single<DataResponse>
}