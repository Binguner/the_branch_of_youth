package com.example.nenguou.youngleague.details_layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.nenguou.youngleague.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class recommentd_articlelayout extends AppCompatActivity {

    private String BigTitle;
    private String University;
    private String CreatedAt;
    private String Fancy;
    private String Detial;
    private TextView BigTitleaty;
    private TextView Universityaty;
    private TextView timeaty;
    private TextView fancyaty;
    private TextView detailaty;
    private View meetingback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articlelayout);
        initId();
        Intent intent = getIntent();
        BigTitle = intent.getStringExtra("BigTitle");
        University = intent.getStringExtra("University");
        CreatedAt = intent.getStringExtra("CreatedAt");
        Fancy = intent.getStringExtra("Fancy");
        Detial = intent.getStringExtra("Detial");

        BigTitleaty.setText(BigTitle);
        Universityaty.setText(University);
        timeaty.setText(stringToDate(CreatedAt));
        fancyaty.setText(Fancy);
        //Html.fromHtml（String string）
        detailaty.setText(Html.fromHtml(Detial));
        meetingback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void initId() {
        meetingback = findViewById(R.id.meetingback);
        BigTitleaty = (TextView) findViewById(R.id.BigTitleaty);
        Universityaty = (TextView) findViewById(R.id.Universityaty);
        timeaty = (TextView) findViewById(R.id.timeaty);
        fancyaty = (TextView) findViewById(R.id.fancyaty);
        detailaty = (TextView) findViewById(R.id.detailaty);
    }
    public static String stringToDate(String lo){
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sd.format(date);
    }
}
