package com.neocafe.neocafewaiter.model.api.connections

import com.neocafe.neocafewaiter.entities.category.CategoryResonse
import com.neocafe.neocafewaiter.entities.menu.MenuResponse
import com.neocafe.neocafewaiter.entities.table.TableResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NewOrderApi {

    @GET("/api-waiter/waiter/tables/")
    suspend fun getTables(): Response<List<TableResponse>>

    @POST("/api-waiter/waiter/order/create/")
    suspend fun makeOrder(): Response<String>

    @GET("/api-waiter/waiter/categories/")
    suspend fun getCategories(): Response<List<CategoryResonse>>

    @GET("/api-waiter/waiter/menu/{category_slug}/")
    suspend fun getMenu(@Path("category_slug") slug: String): Response<List<MenuResponse>>

}