package com.yundin.reddiska.data.api

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.response.PostsResponse
import com.yundin.reddiska.domain.resource.NetworkResource
import retrofit2.http.GET
import retrofit2.http.Header

interface PostsApi {

    @GET("/top")
    fun getTopPosts(@Header("Authorization") token: String): LiveData<NetworkResource<PostsResponse>>
}