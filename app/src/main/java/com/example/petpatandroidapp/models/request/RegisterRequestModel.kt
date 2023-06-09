package com.example.petpatandroidapp.models.request

data class RegisterRequestModel(
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone: String
)