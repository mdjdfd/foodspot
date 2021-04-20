package com.opensource.foodspotlight.repository

import com.opensource.foodspotlight.data.mockapi.MockApiHelper
import com.opensource.foodspotlight.data.model.User
import javax.inject.Inject

class DataRepository @Inject constructor(private val mockApiHelper: MockApiHelper) {
    suspend fun getUsers(): List<User> = mockApiHelper.getUsers()
}