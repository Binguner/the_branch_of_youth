package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Adapter.Annoucement_adapter;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.Utils;
import com.example.nenguou.youngleague.details_layout.announcement_articallayout;
import com.example.nenguou.youngleague.model.Announcement_Model;
import com.example.nenguou.youngleague.model.newsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_announcemnt extends BaseFragent {

    private Context context;
    private List<Announcement_Model> manyNews = new ArrayList<>();
    private Annoucement_adapter annoucement_adapter;
    private PullToRefreshListView announcemennt_List;

    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_announcemrnt;
    }

    @Override
    public void initView() {
        inidId();
        annoucement_adapter = new Annoucement_adapter(getContext(), manyNews);
        announcemennt_List.setAdapter(annoucement_adapter);
        new FirstLoadDataAsyncTask(Fragment_for_announcemnt.this).execute();

        announcemennt_List.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FirstLoadDataAsyncTask(Fragment_for_announcemnt.this).execute();
            }
            int count = 1;
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new LoadMoreDataAsyncTask(Fragment_for_announcemnt.this,count).execute();
                count++;
            }
        });
        announcemennt_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),announcement_articallayout.class);
               // Log.i("titltttt",manyNews.get(0).title);
                Log.i("titltttt",manyNews.get(i-1).title);
                 intent.putExtra("BigTitle", String.valueOf(manyNews.get(i-1).title));
                intent.putExtra("Author", String.valueOf(manyNews.get(i-1).author));
                intent.putExtra("CreatedAt", String.valueOf(manyNews.get(i-1).createdAt));
                intent.putExtra("Content", String.valueOf(manyNews.get(i-1).content));
                startActivity(intent);
            }
        });
        announcemennt_List.setMode(PullToRefreshBase.Mode.BOTH);
    }

    private void inidId() {
        announcemennt_List = (PullToRefreshListView) view.findViewById(R.id.announcemennt_List);
        //FirstLoad();
    }


    class FirstLoadDataAsyncTask extends AsyncTask<Integer, Void, List<Announcement_Model>> {

        private int count = 0;
        private Fragment_for_announcemnt fragment111;
        private String contennt;
        List<Announcement_Model> manyNews;

        public FirstLoadDataAsyncTask(Fragment_for_announcemnt fragment111) {
            this.fragment111 = fragment111;
        }

        @Override
        protected List<Announcement_Model> doInBackground(Integer... integers) {
            if (!Utils.isNetworkAvailable(getContext()))
            {
                Toast.makeText(getContext(),"请检查网络",Toast.LENGTH_SHORT).show();
            }
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS)
                    .build();
            Request request = new Request
                    .Builder()
                     .url("http://47.92.28.251:1024/notice/0/list")
                    //.url("http://47.92.28.251:1024/article/" + count + "/-1/list")
                    .build();
            Response respones = null;
            try {
                respones = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (respones.isSuccessful()) {
                try {
                    contennt = respones.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Announcement_Model>>() {
                }.getType();
                manyNews = gson.fromJson(contennt, type);
                Log.i("zhijie",manyNews.get(0).author);
                Log.i("zhijie",contennt.toString());
                //Log.i("zhijie",manyNews.get(0).createdAt);
            }
            return manyNews;
        }

        @Override
        protected void onPostExecute(List<Announcement_Model> newsModels) {
            super.onPostExecute(newsModels);
            if (fragment111.manyNews.isEmpty()) {
                for (int i = 0; i < manyNews.size(); i++) {
                    fragment111.manyNews.add(manyNews.get(i));
                  //  Log.i("fucknii", manyNews.get(1).createdAt.toString());
                }
                fragment111.annoucement_adapter.notifyDataSetChanged();
                fragment111.announcemennt_List.onRefreshComplete();


            } else if (fragment111.manyNews != null) {
                //Toast.makeText(getContext(), "Test again!", Toast.LENGTH_SHORT).show();
                //Log.i("zhengzuoyu",manyNews.get(0).id);
                 //Log.i("zhengzuoyu",fragment111.manyNews.get(0).id);

            //没有添加新的数据
               // Log.i("hahahahahhaha",fragment111.manyNews.get(0).createdAt);
                //Log.i("hahahahahhaha1",manyNews.get(0).createdAt);
            if (manyNews.get(0).id.equals(fragment111.manyNews.get(0).id)) {
                Toast.makeText(getContext(), "没有发现新数据", Toast.LENGTH_SHORT).show();
                fragment111.announcemennt_List.onRefreshComplete();
            }else {//添加了新数据
                for (int i = 0; i < manyNews.size(); i++) {
                    if (manyNews.get(i).createdAt.equals(fragment111.manyNews.get(0).createdAt )){
                        Log.i("zhengzuoyu","222");
                        int j;
                        for (j = (i-1); j > -1; j--) {
                            fragment111.manyNews.add(manyNews.get(j));
                        }
                        Toast.makeText(getContext(), "新增了"+(j-1)+"条数据", Toast.LENGTH_SHORT).show();
                            fragment111.annoucement_adapter.notifyDataSetChanged();
                             fragment111.announcemennt_List.onRefreshComplete();
                    }
                }
            }
        }

        }
    }

    class LoadMoreDataAsyncTask extends AsyncTask<Integer, Void, List<Announcement_Model>> {

        private int count;
        private Fragment_for_announcemnt fragment111;
        private String contennt;
        List<Announcement_Model> manyNews;

        //构造方法
        public LoadMoreDataAsyncTask(Fragment_for_announcemnt fragment111, int count) {
            this.count = count;
            this.fragment111 = fragment111;
        }

        @Override
        protected List<Announcement_Model> doInBackground(Integer... integers) {
            if (!Utils.isNetworkAvailable(getContext()))
            {
                Toast.makeText(getContext(),"请检查网络",Toast.LENGTH_SHORT).show();
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    // .url("http://47.92.28.251:1024/article/0/-1/list")
                     .url("http://47.92.28.251:1024/notice/1/list")
                     .url("http://47.92.28.251:1024/notice/"+count+"/list")
                    //.url("http://47.92.28.251:1024/article/" + count + "/-1/list")
                    .build();
            Response respones = null;
            try {
                respones = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (respones.isSuccessful()) {
                try {
                    contennt = respones.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<newsModel>>() {
                }.getType();
                manyNews = gson.fromJson(contennt, type);
            }else {
                IOException e = new IOException();
                e.printStackTrace();
                // Toast.makeText(getContext(),"网络情况不好",Toast.LENGTH_SHORT).show();
            }


            return manyNews;
        }

        @Override
        protected void onPostExecute(List<Announcement_Model> newsModels) {
            super.onPostExecute(newsModels);

            for (int i = 0; i < manyNews.size(); i++) {
                fragment111.manyNews.add(manyNews.get(i));
                Log.i("fucknii", manyNews.get(1).createdAt.toString());
            }

            fragment111.annoucement_adapter.notifyDataSetChanged();
            fragment111.announcemennt_List.onRefreshComplete();
        }

    }




    public static Fragment_for_announcemnt getInstance(/*String title*/) {
        Fragment_for_announcemnt mf = new Fragment_for_announcemnt();
        return mf;
    }
}
