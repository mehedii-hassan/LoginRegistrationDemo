package com.example.petpatandroidapp.models.response

data class LoginResponseModel(
    val `data`: Data,
    val message: String,
    val success: Boolean
)