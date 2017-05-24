package com.example.nenguou.youngleague.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.model.Journal_Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Nenguou on 2017/5/19.
 */

public class Journal_adapter extends BaseAdapter {
    private Context context;
    private List<Journal_Model> manyNews;

    public Journal_adapter(Context context, List<Journal_Model> manyNews){
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
        Journal_Model news = manyNews.get(i);
       // Journal_Model.secretary news_secretary = manyNews.get(i).secretary;
       // Journal_Model.secretary news_secretary = manyNews.get(i).secretary
        View v = null;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            v = View.inflate(context, R.layout.item_journal_list, null);
            viewHolder.newsPersonPic = (ImageView) v.findViewById(R.id.journal_Image_View);
            viewHolder.newsPic = (ImageView) v.findViewById(R.id.journal_textPic);
            viewHolder.bigTitle = (TextView) v.findViewById(R.id.journal_bitTitle);
            viewHolder.newsContent = (TextView) v.findViewById(R.id.journal_context);
            viewHolder.newsDate = (TextView) v.findViewById(R.id.journal_date);
            v.setTag(viewHolder);
        } else {
            v = view;
            viewHolder = (ViewHolder) v.getTag();
        }
       // Glide.with(context).load(news.secretary).into(viewHolder.newsPersonPic);
        Glide.with(context).load(news.images).into(viewHolder.newsPic);
        viewHolder.bigTitle.setText(news.title);
        viewHolder.newsContent.setText(news.content);
        viewHolder.newsDate.setText(stringToDate(news.createdAt));
        return v;
    }

    class ViewHolder {
        ImageView newsPersonPic;
        ImageView newsPic;
        TextView bigTitle;
        TextView newsContent;
        TextView newsDate;
    }

    public static String stringToDate(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sd.format(date);
    }
}
