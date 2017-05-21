package com.example.nenguou.youngleague;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nenguou.youngleague.model.UserInfo_login;
import com.example.nenguou.youngleague.model.userinformation;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.nenguou.youngleague.R.id.username_ed;

public class login_activity extends AppCompatActivity {

    private EditText editText_username;
    private EditText editText_password;
    private TextInputLayout username_input;
    private TextInputLayout password_input;
    private Button login_btn;
    private TextView tv_forgetpassword;
    private String htmlStr;
    private TextView result1;
    private TextView result2;
    private String username,password;
    private String textinfo;
    public UserInfo_login userInfo;
    private String contentper;
    private userinformation userinformationfoo;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (!Utils.isNetworkAvailable(login_activity.this))
        {
            Toast.makeText(login_activity.this,"请检查网络",Toast.LENGTH_SHORT).show();
        }
        initId();



        setEditText();

        //暂时不用
        editText_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //username_input.setError("请输入账号");
                if(charSequence.length()==0){
                    //editText_username.setError("请输入账号");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //暂时不用
        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //判断是否输入，以及各种输入情况
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editText_username.getText().toString().trim();
                password = editText_password.getText().toString().trim();

                if(editText_username.getText().length()==0){
                    username_input.setError("请输入账号");
                }
                if(editText_password.getText().length()==0){
                    password_input.setError("请输入密码");
                }
                if(editText_username.getText().length()!=0){
                    username_input.setError("");
                }
                if(editText_password.getText().length()!=0){
                    password_input.setError("");
                }
                if((editText_username.getText().length()!=0)&&(editText_password.getText().length()!=0)){
                    if (!Utils.isNetworkAvailable(login_activity.this))
                    {
                        Toast.makeText(login_activity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                    }
                    //setOkhttp();
                    //tv_forgetpassword.setText(htmlStr);
                    //login_method(view);
                    else{
                        useOkhttp();
                    }

                }
            }
        });
    }

    private void initId() {

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    result1.setText((String) msg.obj);
                    Toast.makeText(login_activity.this,"succeed",Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(login_activity.this,MainActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    result1.setText((String) msg.obj);
                    Toast.makeText(login_activity.this,"failed",Toast.LENGTH_SHORT).show();
                    if(msg.what == 2){
                        result2.setText((String) msg.obj);
                    }
                    break;
                case 2:
                    result2.setText((String) msg.obj);
                  // Toast.makeText(login_activity.this,"failed",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    //不用这个
    private void setOkhttp() {
        final Thread thread = new Thread(){
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String json = "{\n"+
                        "\"username\":\"13546386889@163.com\",\n"+
                        "\"password\":\"100centa30821\"\n"+
                        "}";

                RequestBody body = RequestBody.create(JSON,json);
                Request request = new Request
                        .Builder()
                        .header("Authorization", "Basic YXBwOjEyMzQ1Ng==")
                        //'Authorization':'Basic YXBwOjEyMzQ1Ng=='
                        .url("http://47.92.28.251:1024/oauth/token?grant_type=password")
                        .post(body)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(response.isSuccessful()){
                   Log.i("qwe","succeed");
                }else {
                    //Toast.makeText(login_activity.this,"失败了",Toast.LENGTH_SHORT).show();
                    Log.i("asd","failed");
                }

            }
        };


    }

    //用这个   进行登陆
    private void useOkhttp(){
        final Thread thread = new Thread(){
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                FormBody body = new FormBody.Builder()
                        .add("username",username)
                        .add("password",password)
                        .build();
                final Request request = new Request
                        .Builder()
                        .addHeader("Authorization", "Basic YXBwOjEyMzQ1Ng==")
                        .url("http://47.92.28.251:1024/oauth/token?grant_type=password")
                        .post(body)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!Utils.isNetworkAvailable(login_activity.this))
                {
                    Toast.makeText(login_activity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                }
                if (!Utils.isNetworkAvailable(login_activity.this))
                {
                    Toast.makeText(login_activity.this,"请检查网络",Toast.LENGTH_SHORT).show();
                }

                if(response.isSuccessful()){
                    Message message = new Message();
                    message.what = 0;
                    try {
                        textinfo = response.body().string().toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    message.obj = textinfo;
                    handler.sendMessage(message);
                    Gson gson = new Gson();
                    userInfo = gson.fromJson(textinfo,UserInfo_login.class);
                    System.out.print(userInfo.getToken_type());

                    //获取个人信息
                    final Thread thread3 = new Thread(){
                        @Override
                        public void run() {
                            OkHttpClient client = new OkHttpClient();
                            final Request request = new Request
                                    .Builder()
                                    .url("http://47.92.28.251:1024/user/selfinfo?access_token=" + userInfo.getAccess_token() + "&user_id=" + userInfo.getUser_id())
                                    .build();
                            Response response2 = null;
                            try {
                                response2 = client.newCall(request).execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if(response2.isSuccessful()){
                                try {
                                    contentper = response2.body().string().toString();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Log.i("aaa","okokok");
                        ///        Log.i("ddd",userInfo.getAccess_token());

                                Gson gson = new Gson();
                                userinformationfoo = gson.fromJson(contentper,userinformation.class);
                               // userinformation.branch brancher = userinformationfoo.branch;
                                Log.i("tt",contentper);

                                //db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.youngleague.db/database/stu.db",null);
                                Message msg = new Message();
                                msg.what = 2;
                                msg.obj = contentper;
                                handler.sendMessage(msg);

                                Log.i("testt",userinformationfoo.name+" "+userinformationfoo.email+" "+userinformationfoo.tel+" "+
                                        userinformationfoo.province+" ");

                                //进行数据存储
                                SharedPreferences.Editor editor = getSharedPreferences("personinfo",MODE_PRIVATE).edit();
                                editor.putString("nmae",userinformationfoo.name);
                                editor.putString("email",userinformationfoo.email);
                                editor.putString("tel",userinformationfoo.tel);
                                editor.putString("province",userinformationfoo.province);
                                editor.putString("telWork",userinformationfoo.telWork);
                                editor.commit();

                            }else {
                                Log.i("bbb","faillll");
                            }

                        }
                    };
                    thread3.start();


                }else {
                    Message message = new Message();
                    message.what = 1;
                    try {
                        message.obj = response.body().string().toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(message);
                }
            }
        };
        thread.start();

    }

    private void setEditText() {
        username_input = (TextInputLayout) findViewById(R.id.username_input);
        result1 = (TextView) findViewById(R.id.result1);
        result2 = (TextView) findViewById(R.id.result2);

        password_input = (TextInputLayout) findViewById(R.id.password_input);

        editText_username = (EditText) findViewById(username_ed);
        editText_username.clearFocus();
        editText_username.setSelected(false);
        editText_password = (EditText) findViewById(R.id.password_ed);
        editText_password.clearFocus();
        editText_password.setSelected(false);
        login_btn = (Button) findViewById(R.id.login_btn);
        tv_forgetpassword = (TextView) findViewById(R.id.forgetpassword);
    }




    public void forgetpassword(View view) {
        Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
    }
}
