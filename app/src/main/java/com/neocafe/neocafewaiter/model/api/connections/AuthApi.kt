package com.neocafe.neocafewaiter.model.api.connections

import com.neocafe.neocafewaiter.entities.login.LoginFormRequest
import com.neocafe.neocafewaiter.entities.login.LoginFormResponse
import com.neocafe.neocafewaiter.entities.login.OTPRequest
import com.neocafe.neocafewaiter.entities.login.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api-admin/waiter/login/")
    suspend fun login(@Body loginForm: LoginFormRequest): Response<LoginFormResponse>

    @POST("/api-admin/waiter/check-verification-code/")
    suspend fun otpCheck(@Body otpCode: OTPRequest): Response<Token>

}