package com.neocafe.neocafewaiter.entities.login

import java.io.Serializable

data class Token(
    val refresh: String,
    val access: String
): Serializable
