package com.neocafe.neocafewaiter.entities.table

import java.io.Serializable

data class TableResponse(
    val id: Int,
    val number: Int,
    val status: String
): Serializable
