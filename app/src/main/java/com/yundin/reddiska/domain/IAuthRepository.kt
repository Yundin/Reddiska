package com.yundin.reddiska.domain

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.AppAuthResponse

interface IAuthRepository {
    fun getAppToken(): LiveData<Resource<AppAuthResponse>>
}