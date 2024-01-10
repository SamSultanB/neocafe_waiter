package com.neocafe.neocafewaiter.entities.menu

import java.io.Serializable

data class MenuResponse(
    val id: Int,
    val name: String,
    val price: String,
    val extra_product: List<ExtraProduct>
): Serializable
