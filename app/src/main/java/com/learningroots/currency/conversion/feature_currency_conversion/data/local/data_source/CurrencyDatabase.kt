package com.learningroots.currency.conversion.feature_currency_conversion.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyWithRate
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate

/**
 * CurrencyDatabase - This is the database class.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */


@Database(
    entities = [Rate::class,CurrencyWithRate::class],
    version = 1,
    exportSchema = false
)

abstract class CurrencyDatabase: RoomDatabase() {

    abstract val currencyDao: CurrencyDao

    companion object {
        const val DATABASE_NAME = "currency_db"
    }
}