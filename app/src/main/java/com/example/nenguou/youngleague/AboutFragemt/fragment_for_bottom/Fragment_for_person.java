package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nenguou.youngleague.AboutFragemt.BaseFragent;
import com.example.nenguou.youngleague.R;
import com.example.nenguou.youngleague.person_personinfo_layout;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Nenguou on 2017/5/8.
 */

public class Fragment_for_person extends BaseFragent {

    private RelativeLayout person_btn;
    private RelativeLayout goToPersonSetting;

    private TextView personname;
    private String name;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInfor();
        person_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),person_personinfo_layout.class);
                startActivity(intent);
            }
        });
        goTosetText();
        goToPersonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),aty_person_setting.class);
                startActivity(intent);
            }
        });

    }

    private void goTosetText() {
        personname.setText(name);
    }

    @Override
    public int getLayoutID() {
        return R.layout.bottom_layout_person;
    }

    @Override
    public void initView() {
        goToPersonSetting = (RelativeLayout) view.findViewById(R.id.goToPersonSetting);
        person_btn = (RelativeLayout) view.findViewById(R.id.person_btn);
        personname = (TextView) view.findViewById(R.id.personname);
    }
    public static Fragment_for_person getInstance(/*String title*/) {
        Fragment_for_person mf = new Fragment_for_person();
        //mf.title = title;
        return mf;
    }

    public void getInfor() {
        SharedPreferences pref = getActivity().getSharedPreferences("personinfo",MODE_PRIVATE);
        name = pref.getString("nmae","");
    }
}
