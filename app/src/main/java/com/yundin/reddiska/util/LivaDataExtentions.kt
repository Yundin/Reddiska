package com.yundin.reddiska.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yundin.reddiska.data.Resource

fun <T1, T2> LiveData<T1>.map(mapFun: (T1) -> T2): LiveData<T2> {
    return Transformations.map(this, mapFun)
}

fun <T1 : Resource<*>, T2> LiveData<T1>.mapResource(
    onSuccess: (T1) -> Resource<T2> = { it.adaptType(null) },
    onLoading: (T1) -> Resource<T2> = { it.adaptType(null) },
    onError: (T1) -> Resource<T2> = { it.adaptType(null) }
): LiveData<Resource<T2>> {
    return Transformations.map(this) {
        when {
            it.isSuccess() -> onSuccess(it)
            it.isLoading() -> onLoading(it)
            else -> onError(it)
        }
    }
}

fun <T1, T2> LiveData<T1>.switchMap(mapFun: (T1?) -> LiveData<T2>): LiveData<T2> {
    return Transformations.switchMap(this, mapFun)
}

fun <T1: Resource<*>, T2> LiveData<T1>.switchMapResource(
    onSuccess: (T1) -> LiveData<Resource<T2>> = { MutableLiveData<Resource<T2>>().apply { postValue(it.adaptType()) }},
    onLoading: (T1) -> LiveData<Resource<T2>> = { MutableLiveData<Resource<T2>>().apply { postValue(it.adaptType()) }},
    onError: (T1) -> LiveData<Resource<T2>> = { MutableLiveData<Resource<T2>>().apply { postValue(it.adaptType()) }}
): LiveData<Resource<T2>> {
    return switchMap {
        when {
            it!!.isSuccess() -> onSuccess(it)
            it.isLoading() -> onLoading(it)
            else -> onError(it)
        }
    }
}