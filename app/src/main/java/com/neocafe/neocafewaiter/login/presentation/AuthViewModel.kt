package com.neocafe.neocafewaiter.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafewaiter.auth.login.LoginFormRequest
import com.neocafe.neocafewaiter.auth.login.OTPRequest
import com.neocafe.neocafewaiter.auth.login.Token
import com.neocafe.neocafewaiter.model.api.retrofit.NetworkStatus
import com.neocafe.neocafewaiter.model.api.retrofit.SessionManager
import com.neocafe.neocafewaiter.login.data.repository.AuthRepository
import com.neocafe.neocafewaiter.login.domain.AuthLoginUseCase
import com.neocafe.neocafewaiter.login.domain.LoginForm
import kotlinx.coroutines.launch

class AuthViewModel(
	private val authRepository: AuthRepository,
	private val authLoginUseCase: AuthLoginUseCase,
	private val sessionManager: SessionManager,
) : ViewModel() {
	
	//todo:remember observables should be incapsulated
	val loginReponse: LiveData<NetworkStatus<LoginForm>>
		get() = _loginResponse
	
	private val _loginResponse: MutableLiveData<NetworkStatus<LoginForm>> = MutableLiveData()
	
	val otpResponse: MutableLiveData<NetworkStatus<Token>> = MutableLiveData()
	
	fun login(loginForm: LoginFormRequest) = viewModelScope.launch {
		//todo:always wrap network calls in try catch
		try {
			_loginResponse.postValue(NetworkStatus.Loading())
			val response = authLoginUseCase.requestLogon(loginForm)
			_loginResponse.postValue(NetworkStatus.Success(response))
		} catch (e: Throwable) {
			_loginResponse.postValue(NetworkStatus.Error(e.message ?: "some error"))
		}
	}
	
	fun otpCheck(otpCode: OTPRequest) {
		//todo: rewrite
		viewModelScope.launch {
			otpResponse.postValue(NetworkStatus.Loading())
			val response = authRepository.otpCheck(otpCode)
			if (response.isSuccessful) {
				response.body()?.let {
					otpResponse.postValue(NetworkStatus.Success(it))
				}
				response.body()?.let { sessionManager.saveAuthToken(it.access) }
			} else {
				otpResponse.postValue(NetworkStatus.Error(response.message()))
			}
		}
	}
	
}