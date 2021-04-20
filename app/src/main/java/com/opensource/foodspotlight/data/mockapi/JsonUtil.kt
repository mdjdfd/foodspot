package com.opensource.foodspotlight.data.mockapi

import android.content.Context
import java.io.IOException

class JsonUtil(private val appContext: Context) {
    private lateinit var json: String

    init {
        getJson()
    }

    fun getJson(): String {
        try {
            val inputStream = appContext.assets.open("user_list.json")
            val buffer = ByteArray(inputStream.available())
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        return json
    }

}