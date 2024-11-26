package com.learningroots.currency.conversion.feature_currency_conversion.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Rate - This class works as entity or table for database and works as single object of Rate type.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-6
 */

@Entity
data class Rate(
    @PrimaryKey
    val currency: String,
    val rate: Double
)
