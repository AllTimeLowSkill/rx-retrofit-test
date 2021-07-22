package com.example.rx.services

import com.example.rx.utils.RetrofitConfig
import retrofit2.create

object ApiService {
    private const val baseUrl: String = "https://jsonplaceholder.typicode.com/"
    val apiService: DataServices
    get() = RetrofitConfig.getClient(baseUrl).create()
}