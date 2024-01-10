package com.neocafe.neocafewaiter.entities.profileWaiter

import java.io.Serializable

data class WaiterProfileResponse(
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val date_of_birth: String,

    val monday: Boolean,
    val monday_start_time: String,
    val monday_end_time: String,

    val tuesday: Boolean,
    val tuesday_start_time: String,
    val tuesday_end_time: String,

    val wednesday: Boolean,
    val wednesday_start_time: String,
    val wednesday_end_time: String,

    val thursday: Boolean,
    val thursday_start_time: String,
    val thursday_end_time: String,

    val friday: Boolean,
    val friday_start_time: String,
    val friday_end_time: String,

    val saturday: Boolean,
    val saturday_start_time: String,
    val saturday_end_time: String,

    val sunday: Boolean,
    val sunday_start_time: String,
    val sunday_end_time: String

): Serializable
