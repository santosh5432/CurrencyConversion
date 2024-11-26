package com.learningroots.currency.conversion.feature_currency_conversion.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: Santosh Yadav
 * Created on: 23-11-2024 15:34
 */
@Entity
data class CurrencyWithRate(
    @PrimaryKey
    val currencyCode: String,
    val currency: String,
    val rate: Double
)