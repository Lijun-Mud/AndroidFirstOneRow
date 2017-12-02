package com.example.hb49417.myapplication.sharedata;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb49417.myapplication.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ShareDataActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ShareDataActivity";
    private TextView resultTextview;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShareDataActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_data);

        resultTextview = (TextView) findViewById(R.id.network_result_textview);

        Button callButton= (Button) findViewById(R.id.share_data_try_call_button);
        callButton.setOnClickListener(this);
        Button readContactButton= (Button) findViewById(R.id.share_data_read_contact_button);
        readContactButton.setOnClickListener(this);
        Button sendNotifyButton= (Button) findViewById(R.id.share_data_send_notify_button);
        sendNotifyButton.setOnClickListener(this);
        Button httpConnectionButton= (Button) findViewById(R.id.network_invoke_http_button);
        httpConnectionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_data_try_call_button:
                invokeCallFunction();
                break;
            case R.id.share_data_read_contact_button:
                tryReadContact();
                break;
            case R.id.share_data_send_notify_button:
                sendNotification();
                break;
            case R.id.network_invoke_http_button:
                invokeHttpConnection();
                break;
            default:
        }
    }

    private void invokeHttpConnection() {
        new Thread(()->{
            HttpURLConnection httpURLConnection=null;
            BufferedReader bufferedReader=null;
            try {
                URL url=new URL("http://www.google.com");
                httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(8000);
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb=new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }
                showResponseResult(sb.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(httpURLConnection!=null){
                    httpURLConnection.disconnect();
                }
            }
        }).start();
    }

    private void showResponseResult(String s) {
        runOnUiThread(()->{
            resultTextview.setText(s);
        });
    }

    private void sendNotification() {
        Intent intent=new Intent(this,NotifyForClickedItemActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(ShareDataActivity.this)
                .setContentTitle("This is title")
                .setContentText("This is from content")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .build();
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
    }

    private void tryReadContact() {
        if(ContextCompat.checkSelfPermission(ShareDataActivity.this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ShareDataActivity.this,new String[]{Manifest.permission.READ_CONTACTS},READ_CONTACT_CODE);
        }
        else{
            readContact();
        }
    }

    private void readContact() {
        Cursor cursor=null;
        try {
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String name= cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phone=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.d(TAG, "readContact: "+ name +"\t"+phone);
                }
            }
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }

    }

    private static final int CALL_PHONE_CODE=1;
    private static final int READ_CONTACT_CODE=2;
    private void invokeCallFunction() {
        if(ContextCompat.checkSelfPermission(ShareDataActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ShareDataActivity.this,new String[]{Manifest.permission.CALL_PHONE},CALL_PHONE_CODE);
        }
        else{
            tryCallPhone();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CALL_PHONE_CODE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    tryCallPhone();
                }
                else{
                    Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void tryCallPhone() {
        try{
            Intent intent=new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
}
