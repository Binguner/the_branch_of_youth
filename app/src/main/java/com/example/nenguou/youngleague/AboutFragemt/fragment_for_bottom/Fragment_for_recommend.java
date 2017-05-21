package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_annoucement.Fragment_for_recommend_home;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_annoucement.Fragment_for_recommend_smallclass;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_annoucement.Fragment_for_recommend_studentshow;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_annoucement.Fragment_for_recommend_teachershow;
import com.example.nenguou.youngleague.AboutFragemt.fragment_for_annoucement.Fragment_for_recommend_workcase;
import com.example.nenguou.youngleague.ChildViewPager;
import com.example.nenguou.youngleague.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_recommend extends BaseFragent {

    SlidingTabLayout tablayout;
    ChildViewPager viewPager;
    private ArrayList<BaseFragent> mFagments = new ArrayList<>();
    private String[] mTitles = {"首页", "工作案例", "微党课", "教师支部推荐展示", "学生支部推荐展示"};
    private Toolbar toolbar;
    private MyPagerAdapter adapter;

/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }*/



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mFagments.add(Fragment_for_recommend_home.getInstance());
        mFagments.add(Fragment_for_recommend_workcase.getInstance());
        mFagments.add(Fragment_for_recommend_smallclass.getInstance());
        mFagments.add(Fragment_for_recommend_teachershow.getInstance());
        mFagments.add(Fragment_for_recommend_studentshow.getInstance());

        adapter = new MyPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        tablayout.setViewPager(viewPager,mTitles);

        //toolbar.setLogo(R.drawable.gongchanwhite);

        toolbar.setNavigationIcon(R.drawable.search_white);
        /*toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });*/
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_recommend,menu);
        Log.i("qqqq","d");
        super.onCreateOptionsMenu(menu, inflater);

    }



    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_recommend;
    }


    @Override
    public void initView() {
        initId();
        //mFagments.add(FragmentA.getInstance());

    }

    private void initId() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbarwork);
        tablayout = (SlidingTabLayout) view.findViewById(R.id.tablayout2);
        viewPager = (ChildViewPager) view.findViewById(R.id.view_pager2);

    }



    public static Fragment_for_recommend getInstance(/*String title*/) {
        Fragment_for_recommend mf = new Fragment_for_recommend();
        //mf.title = title;
        return mf;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFagments.get(position);
        }
    }
}
