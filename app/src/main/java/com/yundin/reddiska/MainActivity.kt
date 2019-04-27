package com.yundin.reddiska

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*
import android.webkit.WebViewClient
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.data?.let {
            val start1 = System.currentTimeMillis()
            val params = it.toString().substringAfter('#')
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
            Log.d("HAHAHA1", (System.currentTimeMillis() - start1).toString())

            val start2 = System.currentTimeMillis()
            val new = Uri.parse(it.toString().replace('#', '?'))
            val qpn = new.queryParameterNames
            val paramsMap2 = mutableMapOf<String, String>()
            for (key in qpn) {
                paramsMap2[key] = new.getQueryParameter(key)!!
            }
            Log.d("HAHAHA2", (System.currentTimeMillis() - start2).toString())

            Toast.makeText(this, paramsMap["access_token"], Toast.LENGTH_SHORT).show()
        }

        if (intent.data == null) {
            val webview = WebView(this)
            webview.settings.javaScriptEnabled = true
            webview.webViewClient = object: WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    if (request!!.url.toString().startsWith("yundin.reddit.app")) {
                        Toast.makeText(this@MainActivity, "Got it", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        intent.data = request.url
                        startActivity(intent)
                        finish()
                        return true
                    }
                    return false
                }
            }
            setContentView(webview)
            webview.loadUrl("https://www.reddit.com/api/v1/authorize.compact?client_id=lD712uL-4ow4rw&response_type=token&state=abcdefg&redirect_uri=yundin.reddit.app://redirect&scope=identity, edit, flair, history, modconfig, modflair, modlog, modposts, modwiki, mysubreddits, privatemessages, read, report, save, submit, subscribe, vote, wikiedit, wikiread")
        }
    }
}
