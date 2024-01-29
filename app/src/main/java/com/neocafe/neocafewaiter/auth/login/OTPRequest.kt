package com.neocafe.neocafewaiter.auth.login

import java.io.Serializable

data class OTPRequest(
    val otp: Int
): Serializable
