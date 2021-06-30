package com.opensource.foodspotlight.data.api

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {
    override suspend fun getCurrency() = apiService.getCurrency()
}