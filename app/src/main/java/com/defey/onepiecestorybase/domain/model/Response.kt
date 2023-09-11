package com.defey.onepiecestorybase.domain.model

import com.defey.onepiecestorybase.presentation.utils.Constants.ERROR_NO_ETHERNET_CONNECTION
import com.defey.onepiecestorybase.presentation.utils.Constants.LOG_ERROR_CODE
import com.defey.onepiecestorybase.presentation.utils.Constants.LOG_ERROR_TEXT
import com.defey.onepiecestorybase.presentation.utils.Constants.SERVER
import retrofit2.HttpException
import java.io.IOException

sealed class Response<out T> {
    data class Success<out T>(val value: T) : Response<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorMessage: String?
    ) : Response<Nothing>() {
        fun getErrorDescription(): String =
            "${errorCode?.let { "$LOG_ERROR_CODE ${it}, " } ?: ""}$LOG_ERROR_TEXT $errorMessage"
    }

    object Loading : Response<Nothing>()
}

interface SafeApiCall {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Response<T> {
        return try {
            Response.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val serverUrl = throwable.response()?.let { response ->
                        val url = response.raw().request.url.host
                        "$SERVER $url,"
                    } ?: ""
                    Response.Failure(
                        false,
                        throwable.code(),
                        "$serverUrl ${ throwable.response()?.errorBody().toString() }"
                    )
                }
                is IOException -> {
                    Response.Failure(
                        true,
                        null,
                        "$ERROR_NO_ETHERNET_CONNECTION ${throwable.localizedMessage}"
                    )
                }
                else -> {
                    Response.Failure(
                        false,
                        null,
                        throwable.localizedMessage
                    )
                }
            }
        }
    }
}