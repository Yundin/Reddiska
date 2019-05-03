package com.yundin.reddiska.data.api

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthApi {

    @POST
    fun authorizeApp(@Url url: String, @Field("device_id") deviceId: String, @Field("grant_type") grantType: String = "https://oauth.reddit.com/grants/installed_client"): Single<AppAuthResponse>
}