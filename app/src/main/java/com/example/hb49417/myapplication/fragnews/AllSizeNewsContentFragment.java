package com.example.hb49417.myapplication.fragnews;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hb49417.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllSizeNewsContentFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_size_news_content, container, false);
        return view;
    }

    public void refresh(String title,String content){
        TextView titleTextView= (TextView) view.findViewById(R.id.all_size_news_table_content_title);
        TextView contentTextView= (TextView) view.findViewById(R.id.all_size_news_table_content_content);
        titleTextView.setText(title);
        contentTextView.setText(content);
        ConstraintLayout layout= (ConstraintLayout) view.findViewById(R.id.all_size_news_table_content_root);
        layout.setVisibility(View.VISIBLE);
    }

}
