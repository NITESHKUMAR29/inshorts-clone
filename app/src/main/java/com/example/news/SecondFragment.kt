package com.example.news

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.example.news.adapters.URL

class SecondFragment : Fragment() {
    lateinit var wView:WebView
    private var mURL : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wView=view.findViewById(R.id.wView)
        mURL= arguments?.getString(URL)?:""
        webView()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun webView() {
        wView.webViewClient= WebViewClient()
        wView.apply {
            loadUrl(mURL)
            settings.javaScriptEnabled=true
            settings.safeBrowsingEnabled=true
        }
    }


}