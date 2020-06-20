package com.enigma.si_eo_app.service

import com.enigma.si_eo_app.API.EventAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val BASE_URL = "http://10.0.2.2:8586"
    val instance: EventAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(EventAPI::class.java)
    }

}