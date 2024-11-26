package com.learningroots.currency.conversion.feature_currency_conversion.data.remote.mapper

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.ApiError
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.NetworkError
import retrofit2.HttpException
import java.io.IOException

/**
 * Throwable.toNetworkError - This is the implementation for mapper of network errors.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-6
 */

fun Throwable.toNetworkError(): NetworkError{

    val error = when(this){
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnKnownError
    }

    return NetworkError(
        error = error,
        t = this
    )
}