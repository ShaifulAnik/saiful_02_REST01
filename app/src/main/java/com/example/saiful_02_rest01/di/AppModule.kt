package com.example.saiful_02_rest01.di

import com.example.saiful_02_rest01.network.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    // 1. Retrofit Instance
    single {
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 2. ApiService Instance
    single <ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}