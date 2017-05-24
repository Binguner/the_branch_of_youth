package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.nenguou.youngleague.R;

public class aty_person_setting extends AppCompatActivity {

    private ImageButton backbtn2;
    private RelativeLayout goToChangePassword;
    private RelativeLayout goToMessageAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_person_setting);
        initId();
        goToMessageAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(aty_person_setting.this,aty_person_messagealert.class);
                startActivity(intent);
            }
        });
        goToChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(aty_person_setting.this,aty_person_appset_changepassword.class);
                startActivity(intent);
            }
        });
        backbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initId() {
        goToMessageAlert = (RelativeLayout) findViewById(R.id.goToMessageAlert);
        goToChangePassword = (RelativeLayout) findViewById(R.id.goToChangePassword);
        backbtn2 = (ImageButton) findViewById(R.id.backbtn2);
    }
}
