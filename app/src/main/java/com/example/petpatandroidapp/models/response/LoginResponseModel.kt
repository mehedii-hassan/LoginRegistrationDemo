package com.example.petpatandroidapp.models.response

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("code")
    var code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
)