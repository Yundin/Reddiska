package com.yundin.reddiska

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

@SuppressLint("SetJavaScriptEnabled")
fun WebView.redditLogin(callback: (uri: Uri) -> Unit) {
    settings.javaScriptEnabled = true
    webViewClient = object: WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            if (request!!.url.toString().startsWith("yundin.reddit.app")) {
                callback(request.url)
                return true
            }
            return false
        }
    }
    loadUrl("https://www.reddit.com/api/v1/authorize.compact?client_id=lD712uL-4ow4rw&response_type=token&state=abcdefg&redirect_uri=yundin.reddit.app://redirect&scope=identity, edit, flair, history, modconfig, modflair, modlog, modposts, modwiki, mysubreddits, privatemessages, read, report, save, submit, subscribe, vote, wikiedit, wikiread")
}