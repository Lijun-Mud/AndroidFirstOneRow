package com.example.hb49417.myapplication.fragnews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hb49417.myapplication.R;

public class AllSizeNewsActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AllSizeNewsActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_size_news);
    }
}
