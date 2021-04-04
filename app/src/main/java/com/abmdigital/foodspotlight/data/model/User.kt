package com.abmdigital.foodspotlight.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("user_name")
    val user_name: String,
    @SerializedName("user_image")
    val user_image: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("post_image")
    val post_image: String? = null,
    @SerializedName("other_information")
    val other_information: OtherInformation
)



data class OtherInformation(
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("total_likes")
    val total_likes: Int? = null,
    @SerializedName("total_dislikes")
    val total_dislikes: Int? = null,

)