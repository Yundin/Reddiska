package com.yundin.reddiska.domain

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.PostsResponse

interface IPostsRepository {
    fun getTopPosts(): LiveData<Resource<PostsResponse>>
}