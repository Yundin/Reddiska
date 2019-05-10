package com.yundin.reddiska.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("gone")
fun setGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("image_url")
fun loadImage(view: ImageView, url: String) {
    Glide
            .with(view)
            .load(url)
            .into(view)
}