package com.learningroots.currency.conversion.di

import com.learningroots.currency.conversion.feature_currency_conversion.data.remote.repository.CurrencyRepositoryImpl
import com.learningroots.currency.conversion.feature_currency_conversion.domain.repository.remote.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * RepositoryModule - This is the repository level implementation of DI.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository

}