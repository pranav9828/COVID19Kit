package com.corona.covid_19explorer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WorldCount extends AppCompatActivity {

    String url="https://www.worldometers.info/coronavirus/";
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_count);
        webView = (WebView) findViewById(R.id.website);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
