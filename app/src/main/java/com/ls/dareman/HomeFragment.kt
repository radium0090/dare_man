package com.ls.dareman

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.*
import android.view.KeyEvent.KEYCODE_BACK
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class HomeFragment: DMFragment() {
    lateinit var mySwipeRefreshLayout: SwipeRefreshLayout
    lateinit var mWebView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(com.ls.dareman.R.layout.fragment_top, container, false)

        setWebView(v)
        setRefresh(v)

        return v
    }

    private fun setWebView(v: View) {
        mWebView = v.findViewById(com.ls.dareman.R.id.home_webview) as WebView
        mWebView.webViewClient = WebViewClient()
        setWebView(mWebView)

        val urlStr = activity?.intent?.getStringExtra("url")
        mWebView.loadUrl(urlStr)
        mWebView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KEYCODE_BACK && event.action == MotionEvent.ACTION_UP && mWebView.canGoBack()) {
                    handler.sendEmptyMessage(1)
                    return true
                }
                return false
            }
        })
    }

    private fun setRefresh(v: View) {
        mySwipeRefreshLayout = v.findViewById(com.ls.dareman.R.id.swipeContainer) as SwipeRefreshLayout
        mySwipeRefreshLayout.setOnRefreshListener {
            mWebView.reload()
            mySwipeRefreshLayout.isRefreshing = false
        }
    }

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(message: Message) {
            when (message.what) {
                1 -> {
                    webViewGoBack()
                }
            }
        }
    }

    private fun webViewGoBack() {
        mWebView.goBack()
    }

}