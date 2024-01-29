package com.neocafe.neocafewaiter.login.data.remote

import com.neocafe.neocafewaiter.auth.login.LoginFormRequest
import com.neocafe.neocafewaiter.auth.login.LoginFormResponse
import com.neocafe.neocafewaiter.auth.login.OTPRequest
import com.neocafe.neocafewaiter.auth.login.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
	
	@POST("/api-admin/waiter/login/")
	suspend fun login(
		@Body
		loginForm: LoginFormRequest,
	): LoginFormResponse
	
	@POST("/api-admin/waiter/check-verification-code/")
	suspend fun otpCheck(
		@Body
		otpCode: OTPRequest,
	): Response<Token>
	
}