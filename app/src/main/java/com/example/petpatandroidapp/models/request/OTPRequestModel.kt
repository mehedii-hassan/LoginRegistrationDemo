package com.example.petpatandroidapp.models.request

data class OTPRequestModel(
    val is_forget_password: Boolean,
    val otp: String,
    val phone: String
)