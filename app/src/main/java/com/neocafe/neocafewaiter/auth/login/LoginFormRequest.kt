package com.neocafe.neocafewaiter.auth.login

import java.io.Serializable

data class LoginFormRequest(
    val login: String,
    val password: String
): Serializable
