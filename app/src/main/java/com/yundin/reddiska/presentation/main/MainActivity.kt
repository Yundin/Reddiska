package com.yundin.reddiska.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yundin.reddiska.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.posts.observe(this, Observer {
            text.text = when {
                it.isSuccess() -> it.data!!.joinToString()
                it.isError() -> it.errorMessage!!
                else -> "LOADING"
            }
        })
    }
}
