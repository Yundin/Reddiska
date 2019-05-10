package com.yundin.reddiska.domain.repository

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.response.PostsResponse
import com.yundin.reddiska.domain.resource.Resource

interface IPostsRepository {
    fun getTopPosts(): LiveData<Resource<PostsResponse>>
}