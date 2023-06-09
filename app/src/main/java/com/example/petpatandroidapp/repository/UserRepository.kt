package com.example.petpatandroidapp.repository

import com.example.petpatandroidapp.models.request.LoginRequestModel
import com.example.petpatandroidapp.models.request.RegisterRequestModel
import com.example.petpatandroidapp.models.response.LoginResponseModel
import com.example.petpatandroidapp.models.response.RegisterResponseModel
import com.example.petpatandroidapp.network.ApiClient
import retrofit2.Call

class UserRepository {


    fun userLogin(loginRequestModel: LoginRequestModel): Call<LoginResponseModel>? {

        return ApiClient.retrofitService?.userLogin(loginRequestModel)
    }

    fun userRegistration(registerRequestModel: RegisterRequestModel): Call<RegisterResponseModel>? {
        return ApiClient.retrofitService?.userRegistration(registerRequestModel)
    }
}