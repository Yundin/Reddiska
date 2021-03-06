package com.yundin.reddiska.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val providers: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val vm = providers[modelClass] ?: throw IllegalArgumentException("ViewModel class $modelClass not found")
        @Suppress("UNCHECKED_CAST")
        return vm.get() as T
    }
}