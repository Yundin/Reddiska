package com.yundin.reddiska

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yundin.reddiska.data.Resource
import com.yundin.reddiska.data.repository.AuthRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.data?.let {
            val params = resolveUriParams(it)

            val toastText = params["access_token"] ?: "Error"
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
        }

        if (intent.data == null) {
            val repo = AuthRepository()
            repo.getAppToken()
                .observe(this, Observer {
                    text.text = when {
                        it.status == Resource.Status.SUCCESS -> it.data!!.accessToken
                        it.status == Resource.Status.ERROR -> it.errorMessage!!
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
