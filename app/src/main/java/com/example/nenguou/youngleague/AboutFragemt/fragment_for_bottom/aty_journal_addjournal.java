package com.example.nenguou.youngleague.AboutFragemt.fragment_for_bottom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nenguou.youngleague.R;

public class aty_journal_addjournal extends AppCompatActivity {
    private ImageButton meetingback;
    private EditText editText_for_journal_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_journal_addjournal);
        initId();
        //editText_for_journal_content.setSelection(editText_for_journal_content.getText().toString().length());
        meetingback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onBackPressed();
                finish();
            }
        });
    }

    private void initId() {
        editText_for_journal_content = (EditText) findViewById(R.id.editText_for_journal_content);
        meetingback = (ImageButton) findViewById(R.id.meetingback);
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }*/
}
