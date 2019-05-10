package com.yundin.reddiska.data.response

import com.google.gson.annotations.SerializedName

class AppAuthResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    val scope: String
)