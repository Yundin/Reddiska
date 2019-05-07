package com.yundin.reddiska.data.api

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.Resource
import retrofit2.http.GET
import retrofit2.http.Header

interface PostsApi {

    @GET("/top")
    fun getTopPosts(@Header("Authorization") token: String): LiveData<Resource<PostsResponse>>
}