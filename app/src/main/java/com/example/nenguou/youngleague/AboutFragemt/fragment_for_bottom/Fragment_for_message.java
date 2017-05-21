package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.R;
import com.github.clans.fab.FloatingActionButton;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_message extends BaseFragent {

    private FloatingActionButton message_bty1;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        message_bty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),aty_message_addmeeting.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_messages;
    }

    @Override
    public void initView() {
        message_bty1 = (FloatingActionButton) view.findViewById(R.id.message_bty1);
    }
    public static Fragment_for_message getInstance(/*String title*/) {
        Fragment_for_message mf = new Fragment_for_message();
        //mf.title = title;
        return mf;
    }
    public void setClosedOnTouchOutside(boolean close){

    }
}
