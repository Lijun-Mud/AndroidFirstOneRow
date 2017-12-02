package com.example.hb49417.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    public static void start(Context context,int number,String message) {
        Intent starter = new Intent(context, Main2Activity.class);
        starter.putExtra("int",number);
        starter.putExtra("message",message);
        context.startActivity(starter);
//        if(context instanceof Activity){
//            ((Activity)context).startActivityForResult(starter,2);
//        }
//        else{
//            context.startActivity(starter);
//        }
    }

    public static void startForResult(Activity context,int requestCode,int number,String message) {
        Intent starter = new Intent(context, Main2Activity.class);
        starter.putExtra("int",number);
        starter.putExtra("message",message);
        (context).startActivityForResult(starter,requestCode);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("a","from second");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        Log.d(TAG, "onCreate: "+ (intent.getIntExtra("int",0)));
        Log.i(TAG, "onCreate: " + intent.getStringExtra("message"));
    }
}
