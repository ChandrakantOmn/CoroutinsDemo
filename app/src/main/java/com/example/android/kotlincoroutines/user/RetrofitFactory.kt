package com.example.android.kotlincoroutines.user

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Chandra Kant on 17/10/19.
 */
object RetrofitFactory {
    private const val BASE_URL = "https://api.github.com/"

    fun makeRetrofitService(): UserService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(UserService::class.java)

    }
}