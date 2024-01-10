package com.neocafe.neocafewaiter.model.api.connections

import com.neocafe.neocafewaiter.entities.category.CategoryResonse
import com.neocafe.neocafewaiter.entities.menu.MenuResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApi {

    @GET("/api-waiter/waiter/categories/")
    suspend fun getCategories(): Response<List<CategoryResonse>>

    @GET("/api-waiter/waiter/menu/{category_slug}/")
    suspend fun getMenu(@Path("category_slug") slug: String): Response<List<MenuResponse>>

}