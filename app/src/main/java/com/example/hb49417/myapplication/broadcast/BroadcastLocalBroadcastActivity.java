package com.example.hb49417.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hb49417.myapplication.R;

public class BroadcastLocalBroadcastActivity extends AppCompatActivity {

    public static final String LOCAL_BROADCAST = "com.lijun.broadcast.local.LOCAL_BROADCAST";
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiver localBroadcastReceiver;

    public static void start(Context context) {
        Intent starter = new Intent(context, BroadcastLocalBroadcastActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_local_broadcast);
        localBroadcastManager = LocalBroadcastManager.getInstance(BroadcastLocalBroadcastActivity.this);
        localBroadcastReceiver = new LocalBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter(LOCAL_BROADCAST);
        localBroadcastManager.registerReceiver(localBroadcastReceiver,intentFilter);

        sendLocalBroadcast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

    private void sendLocalBroadcast() {
        Intent intent=new Intent(LOCAL_BROADCAST);
        localBroadcastManager.sendBroadcast(intent);
    }

    class LocalBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"LocalBroadcastReceiver broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}
