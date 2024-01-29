package com.neocafe.neocafewaiter.login.domain

import com.neocafe.neocafewaiter.auth.login.LoginFormRequest
import com.neocafe.neocafewaiter.login.data.repository.AuthRepository

//eg:only for lesson
class AuthLoginUseCase(private val authRepository: AuthRepository) {
	
	suspend fun requestLogon(loginFormRequest: LoginFormRequest) =
		authRepository.login(loginForm = loginFormRequest)
	
}