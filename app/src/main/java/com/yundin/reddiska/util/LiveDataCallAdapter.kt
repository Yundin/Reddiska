package com.yundin.reddiska.util

import androidx.lifecycle.LiveData
import com.yundin.reddiska.data.Resource
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapterFactory: CallAdapter.Factory() {

    companion object {
        fun create() = LiveDataCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val innerType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawInnerType = getRawType(innerType)
        if (rawInnerType != Resource::class.java) {
            throw IllegalArgumentException("LiveData's type must be Resource")
        }
        val resourceType = getParameterUpperBound(0, innerType as ParameterizedType)
        return LiveDataCallAdapter<Any>(resourceType)
    }
}

class LiveDataCallAdapter<R>(private val responseType: Type): CallAdapter<R, LiveData<Resource<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): LiveData<Resource<R>> {
        return object : LiveData<Resource<R>>() {
            val started = AtomicBoolean(false)
            override fun onActive() {
                if (started.compareAndSet(false, true)) {
                    postValue(Resource.loading())
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            if (response.isSuccessful) {
                                postValue(Resource.success(response.body()!!))
                            } else {
                                postValue(Resource.error("Non 2xx"))
                            }
                        }

                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(Resource.error("Network error"))
                        }
                    })
                }
            }
        }
    }
}