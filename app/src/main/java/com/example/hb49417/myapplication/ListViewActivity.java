package com.example.hb49417.myapplication;

import android.content.Context;
import android.content.Intent;
import android.icu.util.TimeZone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ListViewActivity.class);
        context.startActivity(starter);
    }

    private String[] data={"apple","banana"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initializeData();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item=data[position];
                Toast.makeText(ListViewActivity.this,item,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeData() {
        String[] items=getResources().getStringArray(R.array.thing_names);
        List<String> result=new ArrayList<String>();
        for (int k=0;k<50;k++){
            for (int i=0;i<items.length;i++){
                result.add(generateRandomName(items[i]));
            }
        }
        data=result.toArray(new String[0]);
    }

    private String generateRandomName(String name){
        Random random=new Random();
        int length=random.nextInt(10)+1;
        StringBuffer sb=new StringBuffer(name);
        for (int i=0;i<length;i++){
            sb.append(i);
        }
        return sb.toString();
    }
}
