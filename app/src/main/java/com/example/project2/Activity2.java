package com.example.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Activity2 extends AppCompatActivity {

    private TextView tvNickName,tvAccount,tvAge,tvGender,tvCity,tvHome,tvSchool,tvSign,tvBirthdayTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        initData();


    }

    protected void onResume(){
        super.onResume();
        initData();
    }


    private void initView() {
        tvAccount=findViewById(R.id.tv_account_text);
        tvNickName=findViewById(R.id.tv_nick_name);
        tvAge=findViewById(R.id.tv_age);
        tvHome=findViewById(R.id.tv_home_text);
        tvSchool=findViewById(R.id.tv_school_text);
        tvSign=findViewById(R.id.tv_sign_text);
        tvBirthdayTime=findViewById(R.id.tv_birth_time_text);
        tvGender=findViewById(R.id.tv_gender);
        tvCity=findViewById(R.id.tv_city);
    }

    private void initData() {
        getDataFromSpf();
    }

    private void getDataFromSpf() {
        SharedPreferences spfRecord=getSharedPreferences("spfRecord", MODE_PRIVATE);
        String account = spfRecord.getString("nick_name", "");
        String nickName = spfRecord.getString("nick_name", "");
        String city = spfRecord.getString("city", "");
        String gender = spfRecord.getString("gender", "");
        String school = spfRecord.getString("school", "");
        String birthDayTime = spfRecord.getString("birth_day_time", "");
        String sign = spfRecord.getString("sign", "");

        tvAccount.setText(account);
        tvNickName.setText(nickName);
        tvHome.setText(city);
        tvSchool.setText(school);
        tvSign.setText(sign);
        tvBirthdayTime.setText(birthDayTime);
        tvGender.setText(gender);
        tvCity.setText(city);
        String age=getAgeByBirthday(birthDayTime);
        tvAge.setText(age);

    }

    public void logout(View view){
        Intent intent=new Intent(this,MainActivity.class);
        Activity2.this.finish();
        startActivity(intent);
    }
    public void bianji(View view){
        Intent intent=new Intent(this, Activity3.class);
        startActivity(intent);
    }

    private String getAgeByBirthday(String birthDayTime) {
        if(TextUtils.isEmpty(birthDayTime)){
            return "";
        }
        try {
            int index = birthDayTime.indexOf("年");
            String result = birthDayTime.substring(0, index);
            int parseInt = Integer.parseInt(result);
            return String.valueOf((2024-parseInt))+"岁";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}