package com.abmdigital.foodspotlight.data.mockapi

import com.abmdigital.foodspotlight.data.model.User
import javax.inject.Inject

class MockApiHelperImpl @Inject constructor(private val userList: List<User>) : MockApiHelper {
    override suspend fun getUsers() = userList
}