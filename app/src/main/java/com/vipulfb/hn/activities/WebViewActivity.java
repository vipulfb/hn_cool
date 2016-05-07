package com.vipulfb.hn.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vipulfb.hn.AppConstants;
import com.vipulfb.hn.R;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */
public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String header = getIntent().getStringExtra(AppConstants.INTENT_PARAM_WEBVIEW_HEADER);
        String url = getIntent().getStringExtra(AppConstants.INTENT_PARAM_WEBVIEW_URL);

        //Initialize the WebView object with data from the 'activity_webview.xmlxml' file
        final WebView webView = (WebView) findViewById(R.id.wb_webview);
        //Scroll bars should not be hidden
        webView.setScrollbarFadingEnabled(false);
        //Disable the horizontal scroll bar
        webView.setHorizontalScrollBarEnabled(false);
        //Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //Set the user agent
        webView.getSettings().setUserAgentString("AndroidWebView");
        //Clear the cache
        webView.clearCache(true);
        //Make the webview load the specified URL
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                webView.setVisibility(View.VISIBLE);
            }
        });
    }
}
