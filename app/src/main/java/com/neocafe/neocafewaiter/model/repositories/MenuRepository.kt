package com.neocafe.neocafewaiter.model.repositories

import com.neocafe.neocafewaiter.model.api.connections.MenuApi

class MenuRepository(private val menuApi: MenuApi) {

    suspend fun getCategories() = menuApi.getCategories()

    suspend fun getMenu(slug: String) = menuApi.getMenu(slug)

}