package com.example.androidfirstclass;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class News {
    public String title; // 标题
    public String content; //内容
}
public class RecycleActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter ;
    List<News> mNewsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle dataWay = getIntent().getExtras();//从bundle中取出数据
        String way = dataWay.getString(DrawerActivity.WAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_layout);
        mRecyclerView = findViewById(R.id.recyclerview);
        // 构造一些数据
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.title = "标题" + i;
            news.content = "内容" + i;
            mNewsList.add(news);
        }
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        if(way.equals("WAY2")){
                layoutManager = new GridLayoutManager(this,3);
        }
        if(way.equals("WAY1")){
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        }
        if(way.equals("WAY3")){
            DividerItemDecoration mDivider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(mDivider);
        }
        mRecyclerView.setLayoutManager(layoutManager);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {

        @Override
        public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(RecycleActivity.this, R.layout.item_list, null);
            return new MyViewHoder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHoder holder, int position) {
            News news = mNewsList.get(position);
            holder.mTitleTv.setText(news.title);
            holder.mTitleContent.setText(news.content);
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        TextView mTitleContent;

        public MyViewHoder(View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.textView);
            mTitleContent = itemView.findViewById(R.id.textView2);
        }
    }
}
