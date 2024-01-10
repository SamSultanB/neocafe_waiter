package com.neocafe.neocafewaiter.model.repositories

import com.neocafe.neocafewaiter.model.api.connections.NewOrderApi

class NewOrderRepository(private val newOrderApi: NewOrderApi) {

    suspend fun getTables() = newOrderApi.getTables()

    suspend fun getCategories() = newOrderApi.getCategories()

    suspend fun getMenu(slug: String) = newOrderApi.getMenu(slug)

}