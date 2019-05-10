package com.yundin.reddiska.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yundin.reddiska.data.response.PostsResponse
import com.yundin.reddiska.domain.repository.IPostsRepository
import com.yundin.reddiska.domain.resource.Resource
import javax.inject.Inject


class MainViewModel @Inject constructor(postsRepository: IPostsRepository): ViewModel() {

    val posts: LiveData<Resource<PostsResponse>> = postsRepository.getTopPosts()
}