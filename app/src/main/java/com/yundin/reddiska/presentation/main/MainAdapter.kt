package com.yundin.reddiska.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.yundin.reddiska.R
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.api.PostsResponse
import com.yundin.reddiska.presentation.BaseAdapter

class MainAdapter(liveData: LiveData<Resource<PostsResponse>>, lifecycleOwner: LifecycleOwner): BaseAdapter() {

    init {
        liveData.observe(lifecycleOwner, Observer {
            if (it.isSuccess()) {
                data.clear()
                data.addAll(it.data!!.data.children)
                notifyDataSetChanged()
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_post
    }
}