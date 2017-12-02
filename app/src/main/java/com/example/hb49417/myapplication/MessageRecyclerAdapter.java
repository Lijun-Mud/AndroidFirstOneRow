package com.example.hb49417.myapplication;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LIJUN on 7/14/2017.
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {
    private ArrayList<Message> messages=new ArrayList<Message>();

    public MessageRecyclerAdapter(ArrayList<Message> messages){
        this.messages=messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_chat_message_item,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=messages.get(position);
        if (message.getType()==Message.TYPE_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftTextView.setText(message.getContent());
        }
        else if (message.getType()==Message.TYPE_SENT){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightTextView.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout leftLayout;
        ConstraintLayout rightLayout;
        TextView leftTextView;
        TextView rightTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout= (ConstraintLayout) itemView.findViewById(R.id.recycler_chat_item_left_constraintlayout);
            rightLayout= (ConstraintLayout) itemView.findViewById(R.id.recycler_chat_item_right_constraintLayout);
            leftTextView= (TextView) itemView.findViewById(R.id.recycler_chat_item_left_textView);
            rightTextView= (TextView) itemView.findViewById(R.id.recycler_chat_item_right_textView);
        }
    }
}
