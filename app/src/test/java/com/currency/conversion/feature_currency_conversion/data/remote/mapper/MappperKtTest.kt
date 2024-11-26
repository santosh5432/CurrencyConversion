package com.learningroots.currency.conversion.feature_currency_conversion.data.remote.mapper

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.ApiError
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.test.Test

/**
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-12
 */

class MapperTest {
    @Test
    fun networkErrorMapper_ioException_mapsToNetworkError() {
        val exception = IOException()
        val result = exception.toNetworkError()
        assertEquals(ApiError.NetworkError, result.error)
        assertEquals(exception, result.t)
    }

    @Test
    fun networkErrorMapper_httpException_mapsToUnknownResponse() {
        val response = Response.error<String>(500, ResponseBody.create(null, "Error"))
        val exception = HttpException(response)
        val result = exception.toNetworkError()
        assertEquals(ApiError.UnknownResponse, result.error)
        assertEquals(exception, result.t)
    }

    @Test
    fun networkErrorMapper_unknownException_mapsToUnKnownError() {
        val exception = Exception("Unknown error")
        val result = exception.toNetworkError()
        assertEquals(ApiError.UnKnownError, result.error)
        assertEquals(exception, result.t)
    }
}
