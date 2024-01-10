package com.neocafe.neocafewaiter.entities.login

import java.io.Serializable

data class LoginFormRequest(
    val login: String,
    val password: String
): Serializable
