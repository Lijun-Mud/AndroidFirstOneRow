package com.example.hb49417.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hb49417.myapplication.R;

public class BroadcastNetworkFirstActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, BroadcastNetworkFirstActivity.class);
        context.startActivity(starter);
    }

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_network_first);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
        sendMyBroadcast();
    }

    private void sendMyBroadcast() {
        Intent intent=new Intent("com.lijun.broadcast.MY_BROADCAST");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
            String info="Network is "  + (networkInfo!=null && networkInfo.isAvailable()?"available":"unavailable");
            Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
        }
    }
}
