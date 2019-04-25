package com.yundin.reddiska

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        }
    }
}
