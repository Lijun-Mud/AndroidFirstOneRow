package com.example.hb49417.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HB49417 on 14/7/2017.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private final int resourceId;

    public FruitAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            ImageView imageView= (ImageView) view.findViewById(R.id.fruit_item_imageView);
            TextView textView= (TextView) view.findViewById(R.id.fruit_item_textView);
            viewHolder=new ViewHolder();
            viewHolder.imageView=imageView;
            viewHolder.textView=textView;
            view.setTag(viewHolder);
        }
        else{
            view= convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(fruit.getImageId());
        viewHolder.textView.setText(fruit.getName());
        return view;
    }

    class  ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
