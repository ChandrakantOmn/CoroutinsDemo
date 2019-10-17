package com.example.android.kotlincoroutines.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.kotlincoroutines.user.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Chandra Kant on 17/10/19.
 */
class UserRepository(private val network: UserService, private val titleDao: UserDao) {
    val title: LiveData<List<User>> by lazy<LiveData<List<User>>>(LazyThreadSafetyMode.NONE) {
        Transformations.map(titleDao.getUsers()) { it }
    }

    suspend fun getUsers(since: Long, perPage: Int) {
        withContext(Dispatchers.IO) {
            try {
                val result = network.getUsers(since, perPage).await()
                for (user in result) {
                    titleDao.insertUser(user)
                }
            } catch (error: BaseNetworkException) {
                throw TitleRefreshError(error)
            }
        }
    }
}

class TitleRefreshError(cause: Throwable) : Throwable(cause.message, cause)

suspend fun <T> BaseResponse<T>.await(): T {
    return suspendCoroutine { continuation ->
        addOnResultListener { result ->
            when (result) {
                is BaseNetworkSuccess<T> -> continuation.resume(result.data)
                is BaseNetworkError -> continuation.resumeWithException(result.error)
            }
        }
    }
}
