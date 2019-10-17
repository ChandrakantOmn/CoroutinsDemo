package com.example.android.kotlincoroutines.user

import com.example.android.kotlincoroutines.user.network.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Chandra Kant on 17/10/19.
 */
interface UserService {
    @GET("users")
    fun getUsers(@Query("since") since: Long, @Query("per_page") perPage: Int)
            : BaseResponse<List<User>>
}