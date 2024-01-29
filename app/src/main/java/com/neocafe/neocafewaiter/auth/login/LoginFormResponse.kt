package com.neocafe.neocafewaiter.auth.login

import com.google.gson.annotations.SerializedName

//dto
data class LoginFormResponse(
	@SerializedName("message")
	val message: String,
	@SerializedName("phone_number")
	val phoneNumber: String,
)
