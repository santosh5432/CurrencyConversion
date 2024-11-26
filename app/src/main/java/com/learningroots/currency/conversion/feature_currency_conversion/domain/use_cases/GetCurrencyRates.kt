package com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.local.RateRepository
import kotlinx.coroutines.flow.Flow

/**
 * GetCurrencyRates - Use case to fetch currency rates from database.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-7
 */

class GetCurrencyRates(
    private val rateRepository: RateRepository
) {
    operator fun invoke(): Flow<List<Rate>> {

        return rateRepository.getRates()
    }
}