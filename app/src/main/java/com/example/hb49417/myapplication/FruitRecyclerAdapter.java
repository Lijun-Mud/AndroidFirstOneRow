package com.example.hb49417.myapplication;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HB49417 on 14/7/2017.
 */

public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.ViewHolder> {
    private ArrayList<Fruit> fruits;

    public FruitRecyclerAdapter(ArrayList<Fruit> fruits){
        this.fruits=fruits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_recycler,parent,false);
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_recycler_horizontal,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=fruits.get(position);
                Toast.makeText(v.getContext(),"item:"+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=fruits.get(position);
                Toast.makeText(v.getContext(),"image:"+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit= fruits.get(position);
        holder.imageView.setImageResource(fruit.getImageId());
        holder.textView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            imageView= (ImageView) itemView.findViewById(R.id.fruit_item_imageView);
            textView= (TextView) itemView.findViewById(R.id.fruit_item_textView);
        }
    }
}
