package com.example.nenguou.youngleague.details_layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.nenguou.youngleague.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class announcement_articallayout extends AppCompatActivity {

    private String BigTitle;
    private String Author;
    private String CreatedAt;
    private String Content;

    private TextView announcemennt_bittitle;
    private TextView announcemennt_author;
    private TextView announcemennt_time;
    private TextView announcemennt_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_articallayout);
        initId();
        Intent intent = getIntent();
        BigTitle = intent.getStringExtra("BigTitle");
      //  Author = intent.getStringExtra("Author");
       // CreatedAt = intent.getStringExtra("CreatedAt");
        //Content = intent.getStringExtra("Content");

        announcemennt_bittitle.setText(BigTitle);
       /* announcemennt_author.setText(Author);
        announcemennt_time.setText(stringToDate(CreatedAt));
        announcemennt_content.setText(Html.fromHtml(Content));*/
    }

    private void initId() {
        announcemennt_bittitle = (TextView) findViewById(R.id.announcemennt_bittitle);
        announcemennt_author = (TextView) findViewById(R.id.announcemennt_author);
        announcemennt_time = (TextView) findViewById(R.id.announcemennt_time);
        announcemennt_content = (TextView) findViewById(R.id.announcemennt_content);
    }
    public static String stringToDate(String lo){
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sd.format(date);
    }
}
