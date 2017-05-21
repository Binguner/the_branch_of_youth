package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.nenguou.youngleague.R;

public class aty_message_addmeeting extends AppCompatActivity {

    private ImageButton meetingback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_message_addmeeting);
        initId();
        meetingback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initId() {
        meetingback = (ImageButton) findViewById(R.id.meetingback);
    }
}
