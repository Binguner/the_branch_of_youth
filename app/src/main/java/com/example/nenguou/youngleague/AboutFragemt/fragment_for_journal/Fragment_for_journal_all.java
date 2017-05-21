package com.example.nenguou.youngleague.AboutFragemt.fragment_for_journal;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_journal_all extends BaseFragent {

    private PullToRefreshListView newsList_for_journal_all;
    @Override
    public int getLayoutID() {
        return R.layout.journal_all_layout;
    }

    @Override
    public void initView() {
        initId();
    }



    private void initId() {
        //newsList_for_journal_all = (PullToRefreshListView) view.findViewById(R.id.newsList_for_journal_all);
    }

    public static Fragment_for_journal_all getInstance(/*String title*/) {
        Fragment_for_journal_all mf = new Fragment_for_journal_all();
        return mf;
    }
}
