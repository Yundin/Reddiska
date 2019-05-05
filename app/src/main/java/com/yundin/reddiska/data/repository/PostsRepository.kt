package com.yundin.reddiska.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.AuthApi
import com.yundin.reddiska.data.storage.AppStorage
import com.yundin.reddiska.domain.IPostsRepository
import com.yundin.reddiska.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class PostsRepository(app: Application): IPostsRepository {

    private val appStorage = AppStorage(app)
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://oauth.reddit.com")
        .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

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
        val api = retrofit.create(AuthApi::class.java)
        val uuid = UUID.randomUUID().toString()
        val appAuthResponse = api.authorizeApp("https://www.reddit.com/api/v1/access_token", uuid)
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