package com.yundin.reddiska.presentation

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("gone")
fun setGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}