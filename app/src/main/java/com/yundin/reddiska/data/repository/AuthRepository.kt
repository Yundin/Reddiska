package com.yundin.reddiska.data.repository

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.AppAuthResponse
import com.yundin.reddiska.data.api.AuthApi
import com.yundin.reddiska.domain.IAuthRepository
import com.yundin.reddiska.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class AuthRepository: IAuthRepository {

    override fun getAppToken(): LiveData<Resource<AppAuthResponse>> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://oauth.reddit.com")
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(AuthApi::class.java)
        val uuid = UUID.randomUUID().toString()
        return api.authorizeApp("https://www.reddit.com/api/v1/access_token", uuid)
    }
}