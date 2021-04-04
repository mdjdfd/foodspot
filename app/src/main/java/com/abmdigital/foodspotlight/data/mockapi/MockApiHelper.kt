package com.abmdigital.foodspotlight.data.mockapi

import com.abmdigital.foodspotlight.data.model.User

interface MockApiHelper {
    suspend fun getUsers(): List<User>
}