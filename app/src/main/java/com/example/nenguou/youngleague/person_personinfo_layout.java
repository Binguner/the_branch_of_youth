package com.example.nenguou.youngleague;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class person_personinfo_layout extends AppCompatActivity {

    private ImageButton backbtn;
    private TextView zhanghao;
    private TextView shoujihao;
    private TextView xingming;
    private TextView gongzuodianhua;
    private TextView diqv;

    private String zhanghaosf;
    private String shoujihaosf;
    private String xingmingsf;
    private String gongzuodianhuasf;
    private String diqvsf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_personinfo_layout);
        inidId();
        getInforFormSharedPreferences();
        setViewText();
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setViewText() {
        zhanghao.setText(zhanghaosf);
        shoujihao.setText(shoujihaosf);
        xingming.setText(xingmingsf);
        gongzuodianhua.setText(gongzuodianhuasf);
        diqv.setText(diqvsf);
    }

    private void inidId() {
        backbtn = (ImageButton) findViewById(R.id.backbtn);
        zhanghao = (TextView) findViewById(R.id.zhanghao);
        shoujihao = (TextView) findViewById(R.id.shoujihao);
        xingming = (TextView) findViewById(R.id.xingming);
        gongzuodianhua = (TextView) findViewById(R.id.gongzuodianhua);
        diqv = (TextView) findViewById(R.id.diqv);
    }

    public void getInforFormSharedPreferences() {
        SharedPreferences pref = getSharedPreferences("personinfo",MODE_PRIVATE);
        zhanghaosf = pref.getString("email","");
        shoujihaosf = pref.getString("tel","");
        xingmingsf = pref.getString("nmae","");
        gongzuodianhuasf = pref.getString("telWork","");
        diqvsf = pref.getString("province","");
    }
}
