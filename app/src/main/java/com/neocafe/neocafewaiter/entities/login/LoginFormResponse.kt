package com.neocafe.neocafewaiter.entities.login

import java.io.Serializable

data class LoginFormResponse(
    val message: String,
    val phone_number: String
): Serializable
