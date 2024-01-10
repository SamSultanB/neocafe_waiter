package com.neocafe.neocafewaiter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafewaiter.entities.login.LoginFormRequest
import com.neocafe.neocafewaiter.entities.login.LoginFormResponse
import com.neocafe.neocafewaiter.entities.login.OTPRequest
import com.neocafe.neocafewaiter.entities.login.Token
import com.neocafe.neocafewaiter.model.api.retrofit.Resource
import com.neocafe.neocafewaiter.model.api.retrofit.SessionManager
import com.neocafe.neocafewaiter.model.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository, private val sessionManager: SessionManager): ViewModel() {

    val loginResponse: MutableLiveData<Resource<LoginFormResponse>> = MutableLiveData()

    val otpResponse: MutableLiveData<Resource<Token>> = MutableLiveData()

    fun login(loginForm: LoginFormRequest){
        viewModelScope.launch {
            loginResponse.postValue(Resource.Loading())
            val response = authRepository.login(loginForm)
            if(response.isSuccessful){
                response.body()?.let {
                    loginResponse.postValue(Resource.Success(it))
                }
            }else{
                val error = response.errorBody()
                loginResponse.postValue(Resource.Error(error!!.string()))
            }
        }
    }

    fun otpCheck(otpCode: OTPRequest){
        viewModelScope.launch {
            otpResponse.postValue(Resource.Loading())
            val response = authRepository.otpCheck(otpCode)
            if(response.isSuccessful){
                response.body()?.let {
                    otpResponse.postValue(Resource.Success(it))
                }
                response.body()?.let { sessionManager.saveAuthToken(it.access) }
            }else{
                otpResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

}