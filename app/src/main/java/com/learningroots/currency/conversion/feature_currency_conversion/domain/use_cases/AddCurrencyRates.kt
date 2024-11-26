package com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases

import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.local.RateRepository

/**
 * AddCurrencyRates - Use case to insert currency rates into database.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

class AddCurrencyRates(
    private val rateRepository: RateRepository
) {

    suspend operator fun invoke(rate: Rate) {
        rateRepository.insertRates(rate)
    }

}