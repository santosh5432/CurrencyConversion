package com.learningroots.currency.conversion.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Double.roundToTwoDecimalPlaces - Extension function for Double to round values up to two places.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-8
 */

fun Double.roundToTwoDecimalPlaces(): Double {
    return BigDecimal(this)
        .setScale(2, RoundingMode.HALF_UP)
        .toDouble()
}