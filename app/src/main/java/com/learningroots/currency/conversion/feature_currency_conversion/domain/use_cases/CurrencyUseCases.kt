package com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases

/**
 * CurrencyUseCases - Model class to refer all the possible use cases.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-7
 */

data class CurrencyUseCases(
    val addCurrencyRates: AddCurrencyRates,
    val addCurrency: AddCurrency,
    val getCurrency: GetCurrencyRates,
    val getCurrencyWithRates: GetCurrencyWithRates
)