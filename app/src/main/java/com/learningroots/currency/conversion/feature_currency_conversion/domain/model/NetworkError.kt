package com.learningroots.currency.conversion.feature_currency_conversion.domain.model


/**
 * NetworkError - This is the model class for network errors in API response.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

data class NetworkError(
    val error: ApiError,
    val t: Throwable? = null
)


enum class ApiError(val message: String){
    NetworkError("Network Error"),
    UnknownResponse("Unknown Response"),
    UnKnownError("Unknown Error")
}