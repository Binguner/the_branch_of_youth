package com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Adapter.Journal_adapter;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.Utils;
import com.example.nenguou.youngleague.model.Journal_Model;
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

public class Fragment_for_journal_zuzhishenghuo extends BaseFragent {

    private Journal_adapter journal_adapter;
    private List<Journal_Model> manyNews = new ArrayList<>();
    private Context context;
    private PullToRefreshListView newsList_for_journal_zuzhishenghuo;
    @Override
    public int getLayoutID() {
        return R.layout.journal_zuzhishenghuo_layout;
    }

    @Override
    public void initView() {
        initId();
        journal_adapter = new Journal_adapter(getContext(),manyNews);
        newsList_for_journal_zuzhishenghuo.setAdapter(journal_adapter);
        new FirstLoadDataAsyncTask(Fragment_for_journal_zuzhishenghuo.this).execute();
        newsList_for_journal_zuzhishenghuo.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FirstLoadDataAsyncTask(Fragment_for_journal_zuzhishenghuo.this).execute();
            }

            int count = 1;
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new LoadMoreDataAsyncTask(Fragment_for_journal_zuzhishenghuo.this,count).execute();
                count++;
            }
        });
        newsList_for_journal_zuzhishenghuo.setMode(PullToRefreshBase.Mode.BOTH);
    }



    class FirstLoadDataAsyncTask extends AsyncTask<Integer, Void, List<Journal_Model>> {

        private int count = 0;
        private Fragment_for_journal_zuzhishenghuo fragment111;
        private String contennt;
        List<Journal_Model> manyNews;

        public FirstLoadDataAsyncTask(Fragment_for_journal_zuzhishenghuo fragment111) {
            this.fragment111 = fragment111;
        }

        @SuppressLint("WrongConstant")
        @Override
        protected List<Journal_Model> doInBackground(Integer... integers) {
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
                    // .url("http://47.92.28.251:1024/article/0/-1/list")
                    .url("http://47.92.28.251:1024/journal/0/5/list?user_id=9965029")
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
                Type type = new TypeToken<ArrayList<Journal_Model>>() {
                }.getType();
                manyNews = gson.fromJson(contennt, type);
            }
            return manyNews;
        }

        @SuppressLint("WrongConstant")
        @Override
        protected void onPostExecute(List<Journal_Model> journal_models) {
            super.onPostExecute(journal_models);
            if (fragment111.manyNews.isEmpty()) {
                for (int i = 0; i < manyNews.size(); i++) {
                    fragment111.manyNews.add(manyNews.get(i));
                 //   Log.i("fucknii", manyNews.get(1).createdAt.toString());
                }
                fragment111.journal_adapter.notifyDataSetChanged();
                fragment111.newsList_for_journal_zuzhishenghuo.onRefreshComplete();

            } else if (fragment111.manyNews != null) {
                //Toast.makeText(getContext(), "Test again!", Toast.LENGTH_SHORT).show();
                Log.i("zhengzuoyu",manyNews.get(0).id);
                Log.i("zhengzuoyu",fragment111.manyNews.get(0).id);

                //没有添加新的数据
                if (manyNews.get(0).createdAt.equals(fragment111.manyNews.get(0).createdAt)) {
                    Toast.makeText(getContext(), "没有发现新数据", Toast.LENGTH_SHORT).show();
                    fragment111.newsList_for_journal_zuzhishenghuo.onRefreshComplete();
                }else {//添加了新数据
                    for (int i = 0; i < manyNews.size(); i++) {
                        if (manyNews.get(i).createdAt.equals(fragment111.manyNews.get(0).createdAt )){
                            Log.i("zhengzuoyu","222");
                            int j;
                            for (j = (i-1); j > -1; j--) {
                                fragment111.manyNews.add(manyNews.get(j));
                            }
                            Toast.makeText(getContext(), "新增了"+(j-1)+"条数据", Toast.LENGTH_SHORT).show();
                            fragment111.journal_adapter.notifyDataSetChanged();
                            fragment111.newsList_for_journal_zuzhishenghuo.onRefreshComplete();
                        }
                    }
                }
            }

        }
    }

    class LoadMoreDataAsyncTask extends AsyncTask<Integer, Void, List<Journal_Model>> {

        private int count;
        private Fragment_for_journal_zuzhishenghuo fragment111;
        private String contennt;
        List<Journal_Model> manyNews;

        //构造方法
        public LoadMoreDataAsyncTask(Fragment_for_journal_zuzhishenghuo fragment111, int count) {
            this.count = count;
            this.fragment111 = fragment111;
        }

        @SuppressLint("WrongConstant")
        @Override
        protected List<Journal_Model> doInBackground(Integer... integers) {
            if (!Utils.isNetworkAvailable(getContext()))
            {
                Toast.makeText(getContext(),"请检查网络",Toast.LENGTH_SHORT).show();
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    // .url("http://47.92.28.251:1024/article/0/-1/list")
                    //.url("http://47.92.28.251:1024/article/" + count + "/-1/list")
                    .url("http://47.92.28.251:1024/journal/"+count+"/5/list?user_id=9965029")
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
                Type type = new TypeToken<ArrayList<Journal_Model>>() {
                }.getType();
                manyNews = gson.fromJson(contennt, type);
                Log.i("longtest",manyNews.get(0).content);
            }else {
                IOException e = new IOException();
                e.printStackTrace();
            }


            return manyNews;
        }

        @SuppressLint("WrongConstant")
        @Override
        protected void onPostExecute(List<Journal_Model> newsModels) {
            super.onPostExecute(newsModels);
            if(manyNews.isEmpty()){
                Toast.makeText(getContext(),"没有发现新数据",Toast.LENGTH_SHORT).show();
            }else {
                for (int i = 0; i < manyNews.size(); i++) {
                    fragment111.manyNews.add(manyNews.get(i));
                }
            }

            fragment111.journal_adapter.notifyDataSetChanged();
            fragment111.newsList_for_journal_zuzhishenghuo.onRefreshComplete();
        }

    }
    private void initId() {
        newsList_for_journal_zuzhishenghuo = (PullToRefreshListView) view.findViewById(R.id.newsList_for_journal_zuzhishenghuo);
    }

    public static Fragment_for_journal_zuzhishenghuo getInstance(/*String title*/) {
        Fragment_for_journal_zuzhishenghuo mf = new Fragment_for_journal_zuzhishenghuo();
        //mf.title = title;
        return mf;
    }
}
