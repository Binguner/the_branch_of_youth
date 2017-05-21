package com.example.nenguou.youngleague.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.model.newsModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Nenguou on 2017/5/19.
 */

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<newsModel> manyNews;
    public NewsAdapter(Context context,List<newsModel> manyNews){
        this.manyNews = manyNews;
        this.context = context;
    }
    @Override
    public int getCount() {
        return manyNews.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        newsModel news = manyNews.get(i);
        View v = null;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            v = View.inflate(context, R.layout.item_news_list, null);
            viewHolder.bigTitle = (TextView) v.findViewById(R.id.bigTitle);
            viewHolder.eyesNumber = (TextView) v.findViewById(R.id.eyesNumber);
            viewHolder.handNumber = (TextView) v.findViewById(R.id.handNumber);
            viewHolder.newsDate = (TextView) v.findViewById(R.id.newsDate);
            viewHolder.newsPic = (ImageView) v.findViewById(R.id.newsPic);
            v.setTag(viewHolder);
        } else {
            v = view;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.bigTitle.setText(news.title);
        viewHolder.handNumber.setText(news.fancy);
        viewHolder.newsDate.setText(stringToDate(news.createdAt));
        //Glide.with(getContext()).load(news.imageUrl).into(viewHolder.newsPic);
        Glide.with(context).load(news.imageUrl).into(viewHolder.newsPic);
        return v;
    }

    class ViewHolder {
        ImageView newsPic;
        TextView bigTitle;
        TextView eyesNumber;
        TextView handNumber;
        TextView newsDate;
    }
    public static String stringToDate(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sd.format(date);
    }
}
