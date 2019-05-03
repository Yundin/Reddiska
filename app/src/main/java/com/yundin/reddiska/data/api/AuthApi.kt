package com.yundin.reddiska.data.api

import io.reactivex.Single
import retrofit2.http.*

interface AuthApi {

    @Headers("Authorization: Basic bEQ3MTJ1TC00b3c0cnc6")
    @FormUrlEncoded
    @POST
    fun authorizeApp(@Url url: String, @Field("device_id") deviceId: String, @Field("grant_type") grantType: String = "https://oauth.reddit.com/grants/installed_client"): Single<AppAuthResponse>
}