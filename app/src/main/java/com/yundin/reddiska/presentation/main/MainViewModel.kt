package com.yundin.reddiska.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.PostsResponse
import com.yundin.reddiska.domain.IPostsRepository
import javax.inject.Inject


class MainViewModel @Inject constructor(private val postsRepository: IPostsRepository): ViewModel() {

    val posts: LiveData<Resource<PostsResponse>> = postsRepository.getTopPosts()
}