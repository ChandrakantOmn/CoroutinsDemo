package com.example.android.kotlincoroutines.user.network

import android.os.Handler
import android.os.Looper

/**
 * Created by Chandra Kant on 17/10/19.
 */
private val uiHandler = Handler(Looper.getMainLooper())

class BaseResponse<T> {
    var result: BaseResult<T>? = null

    private val listeners = mutableListOf<BaseNetworkListener<T>>()

    fun addOnResultListener(listener: (BaseResult<T>) -> Unit) {
        trySendResult(listener)
        listeners += listener
    }

    fun onSuccess(data: T) {
        result = BaseNetworkSuccess(data)
        sendResultToAllListeners()
    }

    fun onError(throwable: Throwable) {
        result = BaseNetworkError(throwable)
        sendResultToAllListeners()
    }

    private fun sendResultToAllListeners() = listeners.map { trySendResult(it) }


    private fun trySendResult(listener: BaseNetworkListener<T>) {
        val thisResult = result
        thisResult?.let {
            uiHandler.post {
                listener(thisResult)
            }
        }
    }
}

sealed class BaseResult<T>
typealias BaseNetworkListener<T> = (BaseResult<T>) -> Unit

class BaseNetworkError<T>(val error: Throwable) : BaseResult<T>()
class BaseNetworkSuccess<T>(val data: T) : BaseResult<T>()
class BaseNetworkException(message: String) : Throwable(message)
