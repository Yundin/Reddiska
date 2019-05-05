package com.yundin.reddiska.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yundin.reddiska.R
import com.yundin.reddiska.redditLogin
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.data?.let {
            val params = resolveUriParams(it)

            val toastText = params["access_token"] ?: "Error"
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
        }

        if (intent.data == null) {
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

    private fun startLogin() {
        val webView = WebView(this)
        webView.redditLogin { uri ->
            val intent = Intent(this, MainActivity::class.java)
            intent.data = uri
            startActivity(intent)
            finish()
        }
        setContentView(webView)
    }

    private fun resolveUriParams(uri: Uri): Map<String, String> {
        val params = uri.toString().substringAfter('#')
        val paramsMap = mutableMapOf<String, String>()
        var cont = true
        var startIndex = 0
        while (cont) {
            val keyIndex = params.indexOf('=', startIndex)
            val key = params.substring(startIndex, keyIndex)
            var valueIndex = params.indexOf('&', keyIndex)
            if (valueIndex == -1) {
                cont = false
                valueIndex = params.length
            }
            val value = params.substring(keyIndex + 1, valueIndex)
            paramsMap[key] = value
            startIndex = valueIndex + 1
        }
        return paramsMap
    }
}
