package com.neocafe.neocafewaiter.model.repositories

import com.neocafe.neocafewaiter.model.api.connections.ProfileApi

class ProfileRepository(private val profileApi: ProfileApi) {

    suspend fun getProfile() = profileApi.getProfile()

}