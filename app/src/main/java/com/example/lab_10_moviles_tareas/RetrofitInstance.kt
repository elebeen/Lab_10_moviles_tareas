package com.example.lab_10_moviles_tareas

import com.example.lab_10_moviles_tareas.service.ShopApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// app/src/main/java/com/example/tuapp/data/network/RetrofitInstance.kt
object RetrofitInstance {
    private const val BASE_URL = "http://192.168.0.106:8000/"

    val apiService: ShopApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopApiService::class.java)
    }
}