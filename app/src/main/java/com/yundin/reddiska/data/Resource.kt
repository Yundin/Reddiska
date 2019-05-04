package com.yundin.reddiska.data

class Resource<T>(val status: Status, val data: T?, val errorMessage: String?) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null, null)
        fun <T> error(message: String): Resource<T> = Resource(Status.ERROR, null, message)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}