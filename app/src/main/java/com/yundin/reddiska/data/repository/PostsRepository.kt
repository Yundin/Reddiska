package com.yundin.reddiska.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yundin.reddiska.data.api.AuthApi
import com.yundin.reddiska.data.api.PostsApi
import com.yundin.reddiska.data.response.PostsResponse
import com.yundin.reddiska.data.storage.AppStorage
import com.yundin.reddiska.domain.repository.IPostsRepository
import com.yundin.reddiska.domain.resource.Resource
import com.yundin.reddiska.util.mapResource
import com.yundin.reddiska.util.switchMapResource
import java.util.*
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val appStorage: AppStorage,
    private val authApi: AuthApi,
    private val postsApi: PostsApi
): IPostsRepository {

    override fun getTopPosts(): LiveData<Resource<PostsResponse>> {
        return getAppToken()
            .switchMapResource(
                onSuccess = {
                    postsApi.getTopPosts(it.data!!)
                        .switchMapResource(
                            onError = {
                                if (it.errorCode != null) {
                                    reloadAppToken()
                                        .switchMapResource(
                                            onSuccess = { getTopPosts() }
                                        )
                                } else {
                                    MutableLiveData<Resource<PostsResponse>>().apply {
                                        postValue(it.adaptType())
                                    }
                                }
                            }
                        )
                }
            )
    }

    fun getAppToken(): LiveData<Resource<String>> {
        val storedToken = appStorage.getAppToken()
        if (storedToken != null) {
            return MutableLiveData<Resource<String>>().apply {
                postValue(Resource.success(storedToken))
            }
        }
        val uuid = UUID.randomUUID().toString()
        return authApi
            .authorizeApp("https://www.reddit.com/api/v1/access_token", uuid)
            .mapResource(
                onSuccess = {
                    appStorage.saveAppToken(it.data!!.accessToken)
                    Resource.success(it.data.accessToken)
                },
                onError = {
                    Resource.error("Couldn't register app")
                }
            )
    }

    fun reloadAppToken(): LiveData<Resource<String>> {
        val uuid = UUID.randomUUID().toString()
        return authApi
            .authorizeApp("https://www.reddit.com/api/v1/access_token", uuid)
            .mapResource(
                onSuccess = {
                    appStorage.saveAppToken(it.data!!.accessToken)
                    Resource.success(it.data.accessToken)
                },
                onError = {
                    Resource.error("Couldn't register app")
                }
            )
    }
}