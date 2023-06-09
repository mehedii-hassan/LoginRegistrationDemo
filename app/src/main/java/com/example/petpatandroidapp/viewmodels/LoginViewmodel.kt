package com.example.petpatandroidapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petpatandroidapp.models.request.LoginRequestModel
import com.example.petpatandroidapp.models.response.BaseResponse
import com.example.petpatandroidapp.models.response.LoginResponseModel
import com.example.petpatandroidapp.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewmodel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponseModel>> = MutableLiveData()

    fun loginUser(phone: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequestModel(
                    phone = phone,
                    password = pwd
                )
                val response = userRepo.userLogin(loginRequestModel = loginRequest)
                if (response != null) {
                   // loginResult.value = BaseResponse.Success(response.)
                } else {
                   // loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}

/*class LoginViewModel(application: Application) : AndroidViewModel(application) {


    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}*/