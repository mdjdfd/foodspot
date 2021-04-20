package com.opensource.foodspotlight.data.mockapi

import com.opensource.foodspotlight.data.model.User

interface MockApiHelper {
    suspend fun getUsers(): List<User>
}