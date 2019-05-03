package com.yundin.reddiska.data.repository

import com.yundin.reddiska.data.api.AuthApi
import com.yundin.reddiska.domain.IAuthRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class AuthRepository: IAuthRepository {

    override fun getAppToken(): Single<String> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://oauth.reddit.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(AuthApi::class.java)
        val uuid = UUID.randomUUID().toString()
        return api.authorizeApp("https://www.reddit.com/api/v1/access_token", uuid)
            .subscribeOn(Schedulers.io())
            .map { it.accessToken }
    }
}