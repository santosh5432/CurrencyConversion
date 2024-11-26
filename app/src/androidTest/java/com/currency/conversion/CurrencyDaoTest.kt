package com.learningroots.currency.conversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.learningroots.currency.conversion.feature_currency_conversion.data.local.data_source.CurrencyDao
import com.learningroots.currency.conversion.feature_currency_conversion.data.local.data_source.CurrencyDatabase
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.Rate
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrencyDaoTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var currencyDatabase: CurrencyDatabase
    private lateinit var currencyDao: CurrencyDao

    @Before
    fun setUp() {
        currencyDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CurrencyDatabase::class.java
        ).allowMainThreadQueries().build()

        currencyDao = currencyDatabase.currencyDao
    }

    @Test
    fun currencyDao_insertCurrencyRates_expectedSingleCurrencyRate() {
        runBlocking {
            val rate = Rate("INR", 1.0)
            currencyDao.insertCurrencyRates(rate)
            var rateList = emptyList<Rate>()
            currencyDao.getCurrencyRates().collect {
                rateList = it
            }
            Assert.assertEquals(1, rateList.size)
            Assert.assertEquals("INR", rateList[0].currency)
        }

    }

    @After
    fun tearDown() {
        currencyDatabase.close()
    }
}
