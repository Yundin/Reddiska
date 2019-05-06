package com.yundin.reddiska.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.AuthApi
import com.yundin.reddiska.data.storage.AppStorage
import com.yundin.reddiska.domain.IPostsRepository
import java.util.*
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val appStorage: AppStorage,
    private val authApi: AuthApi
): IPostsRepository {

    override fun getTopPosts(): LiveData<Resource<List<String>>> {
        return Transformations.map(getAppToken()) {
            if (it.isSuccess()) {
                return@map Resource.success(arrayListOf<String>().apply {
                    for (i in 0..20) {
                        add(it.data + i)
                    }
                } as List<String>)
            } else if (it.isLoading()) {
                return@map Resource.loading<List<String>>()
            }
            return@map Resource.error<List<String>>(it.errorMessage ?: "Couldn't register app")
        }
    }

    fun getAppToken(): LiveData<Resource<String>> {
        val storedToken = appStorage.getAppToken()
        if (storedToken != null) {
            return MutableLiveData<Resource<String>>().apply {
                value = Resource.success(storedToken)
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
}