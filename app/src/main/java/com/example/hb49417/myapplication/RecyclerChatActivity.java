package com.example.hb49417.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerChatActivity extends AppCompatActivity {


    private ArrayList<Message> messages=new ArrayList<Message>();
    private MessageRecyclerAdapter adapter;
    private EditText sentTextView;
    private RecyclerView recyclerView;

    public static void start(Context context) {
        Intent starter = new Intent(context, RecyclerChatActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_chat);
        initializeMessages();
        recyclerView = (RecyclerView) findViewById(R.id.list_item_recycler_chat_recyclerview);
        sentTextView = (EditText) findViewById(R.id.list_view_recycler_chat_textView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(RecyclerChatActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MessageRecyclerAdapter(messages);
        recyclerView.setAdapter(adapter);

        Button sendButton= (Button) findViewById(R.id.list_view_recycler_chat_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=sentTextView.getText().toString();
                if (!"".equals(content)){
                    Message message=new Message(content,Message.TYPE_SENT);
                    messages.add(message);
                    adapter.notifyItemInserted(messages.size()-1);
                    recyclerView.scrollToPosition(messages.size()-1);
                    sentTextView.setText("");
                }
            }
        });
    }

    private void initializeMessages() {
        messages.add(new Message("Hi Mud",Message.TYPE_RECEIVED));
        messages.add(new Message("Hi Vivian, nice to meet you!",Message.TYPE_SENT));
        messages.add(new Message("Good.............",Message.TYPE_RECEIVED));
    }
}
