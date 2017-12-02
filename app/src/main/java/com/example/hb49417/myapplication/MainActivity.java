package com.example.hb49417.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hb49417.myapplication.broadcast.BroadcastLocalBroadcastActivity;
import com.example.hb49417.myapplication.broadcast.BroadcastNetworkFirstActivity;
import com.example.hb49417.myapplication.demonetwork.NetworkMainActivity;
import com.example.hb49417.myapplication.frag.FragmentDynamicLoadActivity;
import com.example.hb49417.myapplication.fragnews.AllSizeNewsActivity;
import com.example.hb49417.myapplication.sharedata.ShareDataActivity;
import com.example.hb49417.myapplication.tio.FileOutputActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button testButton=(Button)findViewById(R.id.button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Main2Activity.start(MainActivity.this,1,"Activity1");
                Main2Activity.startForResult(MainActivity.this,22,1,"Activity1");
            }
        });

        Button listViewButton=(Button)findViewById(R.id.list_view_button);
        listViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewActivity.start(MainActivity.this);
            }
        });

        Button lisViewDetailButton= (Button) findViewById(R.id.list_view_detail_button);
        lisViewDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewDetailActivity.start(MainActivity.this);
            }
        });

        Button listViewRecyclerButton= (Button) findViewById(R.id.list_view_recycler_button);
        listViewRecyclerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewActivity.start(MainActivity.this);
            }
        });

        Button listViewRecyclerChat= (Button) findViewById(R.id.list_item_recycler_chat_button);
        listViewRecyclerChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerChatActivity.start(MainActivity.this);
            }
        });

        Button fragmentDynamicLoadButton= (Button) findViewById(R.id.fragment_dynamic_load_button);
        fragmentDynamicLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDynamicLoadActivity.start(MainActivity.this);
            }
        });

        Button allSizeButton= (Button) findViewById(R.id.fragment_all_size_news_button);
        allSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllSizeNewsActivity.start(MainActivity.this);
            }
        });

        Button broadcastNetworkFirstButton= (Button) findViewById(R.id.broadcast_network_first_button);
        broadcastNetworkFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BroadcastNetworkFirstActivity.start(MainActivity.this);
            }
        });

        Button broadcastLocalButton= (Button) findViewById(R.id.broadcast_network_local_button);
        broadcastLocalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BroadcastLocalBroadcastActivity.start(MainActivity.this);
            }
        });

        Button fileMainButton= (Button) findViewById(R.id.file_main_button);
        fileMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputActivity.start(MainActivity.this);
            }
        });

        Button shareContentButton= (Button) findViewById(R.id.share_data_content_button);
        shareContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDataActivity.start(MainActivity.this);
            }
        });

        Button networkButton= (Button) findViewById(R.id.network_start_button);
        networkButton.setOnClickListener(v->{
            NetworkMainActivity.start(MainActivity.this);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 22:
                if (resultCode==RESULT_OK){
                    Log.d(TAG, "onActivityResult: "+data.getStringExtra("a"));
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this,"my test2",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: " + "toasted");
            try {
                FileOutputStream stream= openFileOutput("data",(int)MODE_PRIVATE);
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(stream);
                objectOutputStream.writeObject("aaaaaa");
                objectOutputStream.close();
                Log.i(TAG, "onOptionsItemSelected: finish data");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
