package com.template.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.template.databinding.FragmentPageBinding
import com.template.utils.Constants.ARG_OBJECT


class PageFragment : Fragment() {

    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            getString(ARG_OBJECT)?.let {
                setWebView(it)
            }
        }
    }

    private fun setWebView(url: String)= with(binding){

        val webSettings = wVPageFragment.settings
        webSettings.textZoom = 60

        wVPageFragment.apply {

            loadUrl(url)

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    return true
                }
                @Deprecated("Deprecated in Java", ReplaceWith("true"))
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    return true
                }
            }
        }
    }
}