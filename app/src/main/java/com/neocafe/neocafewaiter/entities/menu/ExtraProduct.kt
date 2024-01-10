package com.neocafe.neocafewaiter.entities.menu

import java.io.Serializable

data class ExtraProduct(
    val id: Int,
    val type_extra_product: String,
    val name: String,
    val price: String
): Serializable
