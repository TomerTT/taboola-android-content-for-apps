package com.taboola.android.content_for_apps_sample.webview_in_app;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * The initialization of TaboolaJS is done in the Application class
 * If your project does not have an Application extending class, create one and then init TaboolaJs.
 */

public class WebViewInAppFragment extends Fragment {

    private static final String BASE_URL = "https://www.thestartmagazine.com/feed/summary?&publisherId=Ops_Testing&key=0QzhwJKBfOxouyYgW3woxhwv04inkqWH&countryCode=US&category=Finance&vendor=Reuters,Bloomberg&language=en";

    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Context context = inflater.getContext();
        mWebView = new WebView(context);
        initWebViewSettings(mWebView);
        mWebView.loadUrl(BASE_URL);
        return mWebView;
    }


    private static void initWebViewSettings(WebView webView) {
        final WebSettings settings = webView.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
