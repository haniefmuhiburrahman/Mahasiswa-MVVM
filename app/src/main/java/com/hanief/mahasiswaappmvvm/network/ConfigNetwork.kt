package com.hanief.mahasiswaappmvvm.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {

    companion object {
        fun getRetrofit(): APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2/mentoring_kotlin_week4/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            val service = retrofit.create(APIService::class.java)
            return service
        }
    }
}