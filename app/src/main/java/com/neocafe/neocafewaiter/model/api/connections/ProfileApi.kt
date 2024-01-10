package com.neocafe.neocafewaiter.model.api.connections

import com.neocafe.neocafewaiter.entities.profileWaiter.WaiterProfileResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {

    @GET("/api-waiter/waiter/profile/")
    suspend fun getProfile(): Response<WaiterProfileResponse>

}