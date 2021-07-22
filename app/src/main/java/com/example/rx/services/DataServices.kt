package com.example.rx.services

import com.example.rx.models.DataResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DataServices {
    @GET("posts")
    fun getPosts(): Single<List<DataResponse>>
}