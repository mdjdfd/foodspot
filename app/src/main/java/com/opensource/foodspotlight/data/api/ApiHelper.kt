package com.opensource.foodspotlight.data.api

import com.opensource.foodspotlight.data.model.Currency

interface ApiHelper {
    suspend fun getCurrency(): List<Currency>
}