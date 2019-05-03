package com.yundin.reddiska.domain

import io.reactivex.Single

interface IAuthRepository {
    fun getAppToken(): Single<String>
}