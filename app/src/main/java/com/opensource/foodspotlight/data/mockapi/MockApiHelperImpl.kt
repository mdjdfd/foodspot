package com.opensource.foodspotlight.data.mockapi

import com.opensource.foodspotlight.data.model.User
import javax.inject.Inject

class MockApiHelperImpl @Inject constructor(private val userList: List<User>) : MockApiHelper {
    override suspend fun getUsers() = userList
}