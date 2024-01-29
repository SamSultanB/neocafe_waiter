package com.neocafe.neocafewaiter.login.data.repository

import android.util.Log
import com.neocafe.neocafewaiter.auth.login.LoginFormRequest
import com.neocafe.neocafewaiter.auth.login.LoginFormResponse
import com.neocafe.neocafewaiter.auth.login.OTPRequest
import com.neocafe.neocafewaiter.login.data.remote.AuthApi
import com.neocafe.neocafewaiter.login.domain.LoginForm

class AuthRepository(private val authApi: AuthApi) {
	
	suspend fun login(loginForm: LoginFormRequest): LoginForm = Mapper.fromNetwork(authApi.login(loginForm))
	
	suspend fun otpCheck(otpForm: OTPRequest) = authApi.otpCheck(otpForm)
	
	
	
	object Mapper {
		fun fromNetwork(loginForm: LoginFormResponse) : LoginForm {
			return LoginForm(
				message = loginForm.message ,
				phoneNumber = loginForm.phoneNumber
			)
		}
	}
	
	
}