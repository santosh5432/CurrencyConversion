package com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.remote

import arrow.core.Either
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyRates
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyResponse
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.NetworkError

/**
 * CurrencyRepository - Repository that have abstract methods for API calls.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-7
 */

interface CurrencyRepository {

    suspend fun getCurrencyRates(): Either<NetworkError, CurrencyRates>

    suspend fun getCurrencies(): Either<NetworkError, CurrencyResponse>

}