package com.abmdigital.foodspotlight.repository

import com.abmdigital.foodspotlight.data.mockapi.MockApiHelper
import com.abmdigital.foodspotlight.data.model.User
import javax.inject.Inject

class DataRepository @Inject constructor(private val mockApiHelper: MockApiHelper) {
    suspend fun getUsers(): List<User> = mockApiHelper.getUsers()
}