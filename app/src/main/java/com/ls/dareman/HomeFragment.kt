package com.ls.dareman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.R





class HomeFragment: DMFragment() {

    lateinit var mySwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(com.ls.dareman.R.layout.fragment_top, container, false)

        mySwipeRefreshLayout = v.findViewById(com.ls.dareman.R.id.swipeContainer) as SwipeRefreshLayout

        val mWebView: WebView = v.findViewById(com.ls.dareman.R.id.home_webview) as WebView
        mWebView.webViewClient = WebViewClient()

        setWebView(mWebView)
        val urlStr = activity?.intent?.getStringExtra("url")
        println("sdsdsdsdsdsds 111")
        println(urlStr)

        mWebView.loadUrl(urlStr)

        mySwipeRefreshLayout.setOnRefreshListener {
            mWebView.reload()
            mySwipeRefreshLayout.isRefreshing = false
        }

        return v
    }



}