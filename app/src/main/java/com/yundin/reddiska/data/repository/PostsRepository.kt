package com.yundin.reddiska.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.AuthApi
import com.yundin.reddiska.data.api.PostsApi
import com.yundin.reddiska.data.api.PostsResponse
import com.yundin.reddiska.data.storage.AppStorage
import com.yundin.reddiska.domain.IPostsRepository
import java.util.*
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val appStorage: AppStorage,
    private val authApi: AuthApi,
    private val postsApi: PostsApi
): IPostsRepository {

    override fun getTopPosts(): LiveData<Resource<PostsResponse>> {
        return Transformations.switchMap(getAppToken()) {
            if (it.isSuccess()) {
                val data = postsApi.getTopPosts(it.data!!)
                return@switchMap Transformations.switchMap(data) {
                    if (it.isError() && it.errorMessage == "Non 2xx") {
                        Transformations.switchMap(reloadAppToken()) {
                            getTopPosts()
                        }
                    } else {
                        MutableLiveData<Resource<PostsResponse>>().apply {
                            postValue(it)
                        }
                    }
                }
            } else {
                return@switchMap MutableLiveData<Resource<PostsResponse>>().apply {
                    postValue(Resource(it.status, null, it.errorMessage))
                }
            }
        }
    }

    fun getAppToken(): LiveData<Resource<String>> {
        val storedToken = appStorage.getAppToken()
        if (storedToken != null) {
            return MutableLiveData<Resource<String>>().apply {
                postValue(Resource.success(storedToken))
            }
        }
        val uuid = UUID.randomUUID().toString()
        val appAuthResponse = authApi.authorizeApp("https://www.reddit.com/api/v1/access_token", uuid)
        return Transformations.map(appAuthResponse) {
            if (it.isSuccess()) {
                appStorage.saveAppToken(it.data!!.accessToken)
                return@map Resource.success(it.data.accessToken)
            } else if (it.isLoading()) {
                return@map Resource.loading<String>()
            }
            return@map Resource.error<String>("Couldn't register app")
        }
    }

    fun reloadAppToken(): LiveData<Resource<String>> {
        val uuid = UUID.randomUUID().toString()
        val appAuthResponse = authApi.authorizeApp("https://www.reddit.com/api/v1/access_token", uuid)
        return Transformations.map(appAuthResponse) {
            if (it.isSuccess()) {
                appStorage.saveAppToken(it.data!!.accessToken)
                return@map Resource.success(it.data.accessToken)
            } else if (it.isLoading()) {
                return@map Resource.loading<String>()
            }
            return@map Resource.error<String>("Couldn't register app")
        }
    }
}