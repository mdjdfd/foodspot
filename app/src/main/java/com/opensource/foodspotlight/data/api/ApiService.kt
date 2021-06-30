package com.opensource.foodspotlight.data.api

import com.opensource.foodspotlight.data.model.Currency
import retrofit2.http.GET

interface ApiService {
    @GET("prices?key=a61dd0d39ecfb701d0a3f683a688bb89")
    suspend fun getCurrency(): List<Currency>
}