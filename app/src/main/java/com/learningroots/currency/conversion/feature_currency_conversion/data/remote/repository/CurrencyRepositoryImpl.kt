package com.learningroots.currency.conversion.feature_currency_conversion.data.remote.repository

import arrow.core.Either
import com.learningroots.currency.conversion.BuildConfig
import com.learningroots.currency.conversion.feature_currency_conversion.data.remote.data_source.CurrencyApi
import com.learningroots.currency.conversion.feature_currency_conversion.data.remote.mapper.toNetworkError
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyRates
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyResponse
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.NetworkError
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.remote.CurrencyRepository
import javax.inject.Inject


/**
 * CurrencyRepositoryImpl - This is the implementation for Repository responsible for API calls.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {

    override suspend fun getCurrencyRates(): Either<NetworkError, CurrencyRates> {
        return Either.catch {
            currencyApi.getCurrencyRates(
                query = BuildConfig.APP_ID
            )
        }.mapLeft { it.toNetworkError() }
    }

//    override suspend fun getCurrencies(): Either<NetworkError, CurrencyResponse> {
//        return Either.catch {
//            currencyApi.getCurrencies(
//                query = BuildConfig.APP_ID
//            )
//        }.mapLeft { it.toNetworkError() }
//    }


    override suspend fun getCurrencies(): Either<NetworkError, CurrencyResponse> {
        return Either.catch {
            val response = currencyApi.getCurrencies(query = BuildConfig.APP_ID)
            if (response.isSuccessful && response.body() != null) {
                CurrencyResponse(currencies = response.body()!!) // Map response to CurrencyResponse
            } else {
                throw Exception("Failed to fetch currencies: ${response.errorBody()?.string()}")
            }
        }.mapLeft {
            // Map the exception to a NetworkError
            it.toNetworkError()
        }
    }
}