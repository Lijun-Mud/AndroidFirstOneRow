package com.example.hb49417.myapplication.demonetwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hb49417.myapplication.R;

public class NetworkMainActivity extends AppCompatActivity implements View.OnClickListener {

    public static void start(Context context) {
        Intent starter = new Intent(context, NetworkMainActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_main);
        Button webViewButton= (Button) findViewById(R.id.network_start_webview_button);
        webViewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.network_start_webview_button:
                NetworkWebViewSampleActivity.start(NetworkMainActivity.this);
                break;
            default:
        }
    }
}
