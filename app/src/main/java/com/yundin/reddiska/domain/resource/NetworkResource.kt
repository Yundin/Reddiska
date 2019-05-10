package com.yundin.reddiska.domain.resource

class NetworkResource<T>(status: Status, data: T?, errorMessage: String?, val errorCode: Int?): Resource<T>(status, data, errorMessage) {

    companion object {
        fun <T> success(data: T): NetworkResource<T> = NetworkResource(Status.SUCCESS, data, null, null)
        fun <T> loading(): NetworkResource<T> = NetworkResource(Status.LOADING, null, null, null)
        fun <T> error(message: String, code: Int? = null): NetworkResource<T> = NetworkResource(Status.ERROR, null, message, code)
    }
}