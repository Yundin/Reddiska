package com.yundin.reddiska.data

open class Resource<T>(val status: Status, val data: T?, val errorMessage: String?) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null, null)
        fun <T> error(message: String): Resource<T> = Resource(Status.ERROR, null, message)
    }

    fun isSuccess() = status == Status.SUCCESS
    fun isError() = status == Status.ERROR
    fun isLoading() = status == Status.LOADING

    fun <X> adaptType(mapData: ((T?) -> X?)? = null): Resource<X> {
        if (this is NetworkResource) {
            NetworkResource(status, mapData?.invoke(data), errorMessage, errorCode)
        }
        return Resource(status, mapData?.invoke(data), errorMessage)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
