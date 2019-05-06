package com.yundin.reddiska.di.module

import com.yundin.reddiska.data.repository.PostsRepository
import com.yundin.reddiska.domain.IPostsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        PersistenceModule::class,
        NetworkModule::class
    ]
)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindPostsRepository(postsRepository: PostsRepository): IPostsRepository
}