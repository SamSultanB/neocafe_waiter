package com.neocafe.neocafewaiter.entities.category

import java.io.Serializable

data class CategoryResonse(
    val id: Int,
    val name: String,
    val slug: String
): Serializable
