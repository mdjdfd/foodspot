package com.abmdigital.foodspotlight.data.mockapi

import com.abmdigital.foodspotlight.ApplicationController
import java.io.IOException

class JsonUtil(private val applicationController: ApplicationController) {
    private lateinit var json: String

    init {
        getJson()
    }

    fun getJson(): String {
        try {
            val inputStream = applicationController.assets.open("user_list.json")
            val buffer = ByteArray(inputStream.available())
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        return json
    }

}