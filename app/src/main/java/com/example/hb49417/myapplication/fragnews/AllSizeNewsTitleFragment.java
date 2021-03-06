package com.example.hb49417.myapplication.fragnews;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hb49417.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AllSizeNewsTitleFragment extends Fragment {

    private boolean isTwoPage;
    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.all_size_news_table_fragment_layout)!=null){
            isTwoPage=true;
        }
        else{
            isTwoPage=false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_all_size_news_title, container, false);
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.all_size_news_title_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsRecyclerAdapter adapter=new NewsRecyclerAdapter(getNews());
        recyclerView.setAdapter(adapter);
        return view;
    }

    class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>{

        private List<News> newsList;

        public NewsRecyclerAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.all_size_news_title_item,parent,false);
            final ViewHolder viewHolder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news=newsList.get(viewHolder.getAdapterPosition());
                    if (isTwoPage){
                        AllSizeNewsContentFragment contentFragment= (AllSizeNewsContentFragment) getFragmentManager().
                                findFragmentById(R.id.all_size_news_table_right_fragment);
                        contentFragment.refresh(news.getTitle(),news.getContent());
                    }
                    else{
                        AllSizeNewsContentActivity.start(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News item= newsList.get(position);
            holder.textView.setText(item.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.all_size_news_title_recyclerview_item_textview);
            }
        }
    }
}
