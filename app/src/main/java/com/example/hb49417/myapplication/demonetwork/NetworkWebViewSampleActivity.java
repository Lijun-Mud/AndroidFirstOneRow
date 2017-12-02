package com.example.hb49417.myapplication.demonetwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hb49417.myapplication.R;

public class NetworkWebViewSampleActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, NetworkWebViewSampleActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_web_view_sample);
        WebView webView= (WebView) findViewById(R.id.network_webview_sample);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.google.com");
    }
}
