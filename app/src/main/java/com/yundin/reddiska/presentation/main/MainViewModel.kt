package com.yundin.reddiska.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.repository.PostsRepository
import com.yundin.reddiska.domain.IPostsRepository


class MainViewModel(app: Application): AndroidViewModel(app) {

    private val postsRepository: IPostsRepository
    val posts: LiveData<Resource<List<String>>>

    init {
        postsRepository = PostsRepository(getApplication())
        posts = postsRepository.getTopPosts()
    }
}