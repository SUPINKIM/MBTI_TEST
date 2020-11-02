package com.donggi.mbtitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MyWebView extends AppCompatActivity {

    private WebView mWebView; //Webview 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywebview);

        //웹뷰 설정
        mWebView = (WebView) findViewById(R.id.webview);


        WebSettings mWebSettings = mWebView.getSettings();

        //mWebView.setWebChromeClient(new WebChromeClient());

        //mWebView.getSettings().setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptEnabled(true);

        mWebSettings.setAllowContentAccess(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setUseWideViewPort(true);
        mWebView.setWebViewClient(new WebViewClient());//새창열기 없이 웹뷰 내에서 다시 열기//페이지 이동 원활히 하기위해 사용
        mWebView.loadUrl("https://www.16personalities.com/ko/무료-성격-유형-검사");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}

