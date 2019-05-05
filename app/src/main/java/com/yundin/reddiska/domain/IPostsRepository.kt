package com.yundin.reddiska.domain

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.Resource

interface IPostsRepository {
    fun getTopPosts(): LiveData<Resource<List<String>>>
}