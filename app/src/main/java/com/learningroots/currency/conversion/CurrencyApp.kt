package com.learningroots.currency.conversion

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The CurrencyApp class serves as the Application class for this Android app.
 *
 * It is annotated with @HiltAndroidApp to trigger Hilt's code generation,
 * including the base class for dependency injection. This class acts as the
 * container for managing dependencies across the entire app lifecycle.
 *
 * @author Santosh Yadav
 * @version 1.0
 * @since 2024-10-5
 */

@HiltAndroidApp
class CurrencyApp: Application() {
}