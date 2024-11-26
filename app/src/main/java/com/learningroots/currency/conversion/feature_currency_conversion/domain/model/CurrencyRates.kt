package com.learningroots.currency.conversion.feature_currency_conversion.domain.model

/**
 * CurrencyRates - This is the model class for latest.json endpoint API response.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

data class CurrencyRates(
    val base: String,
    val rates: Map<String, Double>
)

