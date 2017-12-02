package com.example.hb49417.myapplication.fragnews;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hb49417.myapplication.R;

public class AllSizeNewsContentActivity extends AppCompatActivity {

    public static void start(Context context,String title,String content) {
        Intent starter = new Intent(context, AllSizeNewsContentActivity.class);
        starter.putExtra("title",title);
        starter.putExtra("content",content);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_size_news_content);
        AllSizeNewsContentFragment fragment= (AllSizeNewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.all_size_news_table_fragment_content);
        fragment.refresh(getIntent().getStringExtra("title"),getIntent().getStringExtra("content"));
    }
}
