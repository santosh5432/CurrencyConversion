package com.learningroots.currency.conversion.feature_currency_conversion.data.remote.data_source

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyRates
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * CurrencyApi - This is the API interface.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

interface CurrencyApi {

    @GET("latest.json")
    suspend fun getCurrencyRates(
        @Query("app_id") query: String,
    ): CurrencyRates

    @GET("currencies.json")
    suspend fun getCurrencies(
        @Query("app_id") query: String
    ): Response<Map<String, String>>

}