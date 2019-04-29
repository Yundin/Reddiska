package com.yundin.reddiska

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.data?.let {
            val params = resolveUriParams(it)

            val toastText = params["access_token"] ?: "Error"
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
        }

        if (intent.data == null) {
            val webView = WebView(this)
            webView.redditLogin { uri ->
                val intent = Intent(this, MainActivity::class.java)
                intent.data = uri
                startActivity(intent)
                finish()
            }
            setContentView(webView)
        }
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
