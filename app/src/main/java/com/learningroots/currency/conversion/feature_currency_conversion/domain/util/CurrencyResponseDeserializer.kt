package com.learningroots.currency.conversion.feature_currency_conversion.domain.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.learningroots.currency.conversion.feature_currency_conversion.domain.model.CurrencyResponse
import java.lang.reflect.Type

/**
 * Author: Santosh Yadav
 * Created on: 24-11-2024 05:38
 */
class CurrencyResponseDeserializer : JsonDeserializer<CurrencyResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CurrencyResponse {
        val currencies = mutableMapOf<String, String>()
        val jsonObject = json.asJsonObject

        // Populate the map with key-value pairs from the flat JSON
        for ((key, value) in jsonObject.entrySet()) {
            currencies[key] = value.asString
        }

        return CurrencyResponse(currencies = currencies)
    }
}