package com.dogood.tamilblogs.data

import com.squareup.moshi.Json


data class RequestTokenEntity(
    @Json(name = "success")
    val success:Boolean,
    @Json(name = "expires_at")
    val expires_at:String,
    @Json(name = "request_token")
    val request_token:String
)