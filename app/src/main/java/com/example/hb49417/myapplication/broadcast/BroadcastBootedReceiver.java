package com.example.hb49417.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LIJUN on 7/22/2017.
 */

public class BroadcastBootedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"booted from booted broadcast",Toast.LENGTH_SHORT).show();
    }
}
