package com.opensource.foodspotlight.data.model

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("currency")
    val currency: String,

    @SerializedName("price")
    val price: String
)