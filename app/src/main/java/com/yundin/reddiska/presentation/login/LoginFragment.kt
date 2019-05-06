package com.yundin.reddiska.presentation.login

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yundin.reddiska.redditLogin

class LoginFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val webView = WebView(context)
        webView.redditLogin { uri ->
            val paramsMap = resolveUriParams(uri)
            Toast.makeText(context, paramsMap["access_token"] ?: "Error", Toast.LENGTH_SHORT).show()
        }
        return webView
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