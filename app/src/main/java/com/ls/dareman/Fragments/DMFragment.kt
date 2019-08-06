package com.ls.dareman.Fragments

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment

open class DMFragment: Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(webView: WebView) {
        val setting = webView.settings

        setting.javaScriptEnabled = true
        setting.javaScriptCanOpenWindowsAutomatically = true
        setting.allowFileAccess = true// 设置允许访问文件数据
        setting.setSupportZoom(true)//支持缩放
        setting.javaScriptCanOpenWindowsAutomatically = true
        setting.cacheMode = WebSettings.LOAD_DEFAULT
        setting.domStorageEnabled = true
        setting.databaseEnabled = true
    }
}