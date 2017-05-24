package com.example.nenguou.youngleague.AboutFragemt.fragment_for_annoucement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.Adapter.NewsAdapter;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.Utils;
import com.example.nenguou.youngleague.details_layout.recommentd_articlelayout;
import com.example.nenguou.youngleague.model.newsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_recommend_smallclass extends BaseFragent {

    private PullToRefreshListView newsList;
    private Context context;
    private List<newsModel> manyNews = new ArrayList<>();
    private NewsAdapter newsadapter;
    @Override
    public int getLayoutID() {
        return R.layout.recommend_smallclass_layout;
    }

    @Override
    public void initView() {
        initId();
        newsadapter = new NewsAdapter(getContext(), manyNews);
        newsList.setAdapter(newsadapter);
        new FirstLoadDataAsyncTask(Fragment_for_recommend_smallclass.this).execute();
        // item 点击事件
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "haha", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), recommentd_articlelayout.class);
                intent.putExtra("BigTitle", String.valueOf(manyNews.get(i).title));
                intent.putExtra("University", String.valueOf(manyNews.get(i).university));
                intent.putExtra("CreatedAt", String.valueOf(manyNews.get(i).createdAt));
                intent.putExtra("Fancy", String.valueOf(manyNews.get(i).fancy));
                intent.putExtra("Detial", String.valueOf(manyNews.get(i).detail));
                startActivity(intent);
            }
        });

        // 刷新事件
        newsList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new FirstLoadDataAsyncTask(Fragment_for_recommend_smallclass.this).execute();
            }

            int count = 1;
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                Log.i("ccount", "countt");
                new LoadMoreDataAsyncTask(Fragment_for_recommend_smallclass.this, count).execute();
                count++;
            }
        });
        newsList.setMode(PullToRefreshBase.Mode.BOTH);
    }

    class FirstLoadDataAsyncTask extends AsyncTask<Integer, Void, List<newsModel>> {

        private int count = 0;
        private Fragment_for_recommend_smallclass fragment111;
        private String contennt;
        List<newsModel> manyNews;

        public FirstLoadDataAsyncTask(Fragment_for_recommend_smallclass fragment111) {
            this.fragment111 = fragment111;
        }

        @Override
        protected List<newsModel> doInBackground(Integer... integers) {
            if (!Utils.isNetworkAvailable(getContext()))
            {
                Toast.makeText(getContext(),"请检查网络",Toast.LENGTH_SHORT).show();
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    // .url("http://47.92.28.251:1024/article/0/-1/list")
                    .url("http://47.92.28.251:1024/article/" + count + "/1/list")
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
            }
            return manyNews;
        }

        @Override
        protected void onPostExecute(List<newsModel> newsModels) {
            super.onPostExecute(newsModels);
            if (fragment111.manyNews.isEmpty()) {
                for (int i = 0; i < manyNews.size(); i++) {
                    fragment111.manyNews.add(manyNews.get(i));
                    Log.i("fucknii", manyNews.get(1).createdAt.toString());
                }
                fragment111.newsadapter.notifyDataSetChanged();
                fragment111.newsList.onRefreshComplete();

            } else if (fragment111.manyNews != null) {
                //Toast.makeText(getContext(), "Test again!", Toast.LENGTH_SHORT).show();
                Log.i("zhengzuoyu",manyNews.get(0).id);
                Log.i("zhengzuoyu",fragment111.manyNews.get(0).id);

                //没有添加新的数据
                if (manyNews.get(0).createdAt.equals(fragment111.manyNews.get(0).createdAt)) {
                    Log.i("zhengzuoyu","111");
                    Toast.makeText(getContext(), "没有发现新数据", Toast.LENGTH_SHORT).show();
                    fragment111.newsList.onRefreshComplete();
                }else {//添加了新数据
                    for (int i = 0; i < manyNews.size(); i++) {
                        if (manyNews.get(i).createdAt.equals(fragment111.manyNews.get(0).createdAt )){
                            Log.i("zhengzuoyu","222");
                            int j;
                            //for (j = 0; j < i; j++) {
                            for (j = (i-1); j > -1; j--) {

                                fragment111.manyNews.add(manyNews.get(j));
                            }
                            Toast.makeText(getContext(), "新增了"+(j-1)+"条数据", Toast.LENGTH_SHORT).show();
                            fragment111.newsadapter.notifyDataSetChanged();
                            fragment111.newsList.onRefreshComplete();
                        }
                    }
                }
            }

        }
    }

    class LoadMoreDataAsyncTask extends AsyncTask<Integer, Void, List<newsModel>> {

        private int count;
        private Fragment_for_recommend_smallclass fragment111;
        private String contennt;
        List<newsModel> manyNews;

        //构造方法
        public LoadMoreDataAsyncTask(Fragment_for_recommend_smallclass fragment111, int count) {
            this.count = count;
            this.fragment111 = fragment111;
        }

        @SuppressLint("WrongConstant")
        @Override
        protected List<newsModel> doInBackground(Integer... integers) {
            if (!Utils.isNetworkAvailable(getContext()))
            {
                Toast.makeText(getContext(),"请检查网络",Toast.LENGTH_SHORT).show();
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    // .url("http://47.92.28.251:1024/article/0/-1/list")
                    .url("http://47.92.28.251:1024/article/" + count + "/1/list")
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
            }

            return manyNews;
        }

        @Override
        protected void onPostExecute(List<newsModel> newsModels) {
            super.onPostExecute(newsModels);

                for (int i = 0; i < manyNews.size(); i++) {
                    fragment111.manyNews.add(manyNews.get(i));
                    Log.i("fucknii", manyNews.get(1).createdAt.toString());
                }

            fragment111.newsadapter.notifyDataSetChanged();
            fragment111.newsList.onRefreshComplete();
        }

    }


    private void initId() {
        newsList = (PullToRefreshListView) view.findViewById(R.id.newsListforsmallclass);
    }

    public static Fragment_for_recommend_smallclass getInstance(/*String title*/) {
        Fragment_for_recommend_smallclass mf = new Fragment_for_recommend_smallclass();
        //mf.title = title;
        return mf;
    }
}
