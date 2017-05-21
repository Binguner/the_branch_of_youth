package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.nenguou.youngleague.R;

public class aty_person_messagealert extends AppCompatActivity {

    private ImageButton backbtn3;  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_person_messagealert);
        initId();
        backbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initId() {
        backbtn3 = (ImageButton) findViewById(R.id.backbtn3);
    }
}
