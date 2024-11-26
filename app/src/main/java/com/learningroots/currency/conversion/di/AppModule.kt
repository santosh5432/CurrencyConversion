package com.learningroots.currency.conversion.di

import android.app.Application
import androidx.room.Room
import com.learningroots.currency.conversion.feature_currency_conversion.data.local.data_source.CurrencyDatabase
import com.learningroots.currency.conversion.feature_currency_conversion.data.local.repository.RateRepositoryImpl
import com.learningroots.currency.conversion.feature_currency_conversion.data.remote.data_source.CurrencyApi
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.local.RateRepository
import com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases.AddCurrency
import com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases.AddCurrencyRates
import com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases.CurrencyUseCases
import com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases.GetCurrencyRates
import com.learningroots.currency.conversion.feature_currency_conversion.domain.use_cases.GetCurrencyWithRates
import com.learningroots.currency.conversion.feature_currency_conversion.presentation.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * AppModule - This is the module level implementation of DI.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyDatabase(app: Application): CurrencyDatabase {
        return Room.databaseBuilder(
            app,
            CurrencyDatabase::class.java,
            CurrencyDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: CurrencyDatabase): RateRepository {
        return RateRepositoryImpl(db.currencyDao)
    }

    @Provides
    @Singleton
    fun provideCurrencyUseCases(repository: RateRepository): CurrencyUseCases {
        return CurrencyUseCases(
            getCurrency = GetCurrencyRates(repository),
            addCurrencyRates = AddCurrencyRates(repository),
            addCurrency = AddCurrency(repository),
            getCurrencyWithRates = GetCurrencyWithRates(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCurrencyApi():CurrencyApi{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
        return retrofit
    }
}