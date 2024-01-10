package com.neocafe.neocafewaiter.entities.login

import java.io.Serializable

data class OTPRequest(
    val otp: Int
): Serializable
