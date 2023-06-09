package com.example.petpatandroidapp.network

import com.example.petpatandroidapp.models.request.LoginRequestModel
import com.example.petpatandroidapp.models.request.RegisterRequestModel
import com.example.petpatandroidapp.models.response.LoginResponseModel
import com.example.petpatandroidapp.models.response.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServiceApi {

    @POST("/api/v1/auth/user-register")
    fun userRegistration(@Body registrationRequest: RegisterRequestModel): Call<RegisterResponseModel>

    @POST("/api/v1/auth/login")
    fun userLogin(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModel>

}