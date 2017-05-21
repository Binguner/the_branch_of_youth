package com.example.nenguou.youngleague.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.model.Announcement_Model;

import java.util.List;

/**
 * Created by Nenguou on 2017/5/19.
 */

public class Annoucement_adapter extends BaseAdapter {
    private Context context;
    private List<Announcement_Model> manyNews;

    public Annoucement_adapter(Context context, List<Announcement_Model> manyNews){
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
        Announcement_Model news = manyNews.get(i);
        View v = null;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            v = View.inflate(context, R.layout.item_annoucement_list, null);
            viewHolder.bigTitle = (TextView) v.findViewById(R.id.bigTitle_announcement);
            viewHolder.eyesNumber = (TextView) v.findViewById(R.id.anthor_annoucement);
            viewHolder.newsPic = (ImageView) v.findViewById(R.id.newsPic_annoucement);
            v.setTag(viewHolder);
        } else {
            v = view;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.bigTitle.setText(news.title);
        viewHolder.eyesNumber.setText(news.author);
        //Glide.with(getContext()).load(news.imageUrl).into(viewHolder.newsPic);
        Glide.with(context).load(news.imgUrl).into(viewHolder.newsPic);
        return v;
    }

    class ViewHolder {
        ImageView newsPic;
        TextView bigTitle;
        TextView eyesNumber;

    }
}
