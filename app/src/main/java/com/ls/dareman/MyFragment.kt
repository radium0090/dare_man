package com.ls.dareman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class MyFragment: DMFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_my, container, false)
        val mWebView: WebView = v.findViewById(R.id.my_webview) as WebView
        mWebView.webViewClient = WebViewClient()
        setWebView(mWebView)

        val urlStr = activity?.intent?.getStringExtra("url")
        println("sdsdsdsdsdsds 333")
        println(urlStr)

        mWebView.loadUrl("https://quinz.me")
        return v
    }
}