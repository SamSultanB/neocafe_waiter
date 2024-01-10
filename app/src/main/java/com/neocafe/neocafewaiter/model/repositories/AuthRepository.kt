package com.neocafe.neocafewaiter.model.repositories

import com.neocafe.neocafewaiter.entities.login.LoginFormRequest
import com.neocafe.neocafewaiter.entities.login.OTPRequest
import com.neocafe.neocafewaiter.model.api.connections.AuthApi

class AuthRepository(private val authApi: AuthApi) {

    suspend fun login(loginForm: LoginFormRequest) = authApi.login(loginForm)

    suspend fun otpCheck(otpForm: OTPRequest) = authApi.otpCheck(otpForm)

}