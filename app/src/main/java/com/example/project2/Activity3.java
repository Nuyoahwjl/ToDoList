package com.example.project2;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity3 extends AppCompatActivity {

    private EditText etNickName,etAccount,etSchool,etSign;
    private TextView tvBirthDayTime;
    private RadioButton rbBoy,rbGirl;
    private AppCompatSpinner spinnerCity;

    private String[] cities;
    private int selectedCityPosition;
    private String selectedCity;
    private String birthDay;
    private ImageView calendar;

    private int Year,Month,Day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        initData();
        initEvent();
    }

    private void initView() {
//        etAccount=findViewById(R.id.et_account_text);
        etNickName=findViewById(R.id.et_nick_name);
        etSchool=findViewById(R.id.et_school_text);
        etSign=findViewById(R.id.et_sign_text);

        tvBirthDayTime=findViewById(R.id.tv_birth_time_text);

        rbBoy=findViewById(R.id.rb_boy);
        rbGirl=findViewById(R.id.rb_girl);

        spinnerCity=findViewById(R.id.sp_city);

        calendar=findViewById(R.id.calendar);

    }

    private void initData() {
            cities=getResources().getStringArray(R.array.cities);
        getDataFromSpf();
    }

    private void initEvent() {
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCityPosition=position;
                selectedCity=cities[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Activity3.this,new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth){
                        birthDay=year+"年"+(month+1)+"月"+dayOfMonth+"日";
                        tvBirthDayTime.setText(birthDay);
                        Year=year;Month=month;Day=dayOfMonth;
                    }
                },2005,11,24).show();
            }
        });
    }

    public void save(View view){

//        String account=etAccount.getText().toString();
        String sign=etSign.getText().toString();
        String school=etSchool.getText().toString();
        String nick_name=etNickName.getText().toString();

        SharedPreferences spfRecord=getSharedPreferences("spfRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit=spfRecord.edit();

//        edit.putString("account",account);
        edit.putString("sign",sign);
        edit.putString("school",school);
        edit.putString("nick_name",nick_name);
        edit.putString("birth_day_time",birthDay);
        edit.putString("city",selectedCity);

        String gender="";
        if(rbBoy.isChecked()){
            gender="男";
        }
        if(rbGirl.isChecked()){
            gender="女";
        }
        edit.putString("gender",gender);
        edit.apply();
        this.finish();

    }

    private void getDataFromSpf() {
        SharedPreferences spfRecord=getSharedPreferences("spfRecord", MODE_PRIVATE);
//        String account = spfRecord.getString("account", "");
        String nickName = spfRecord.getString("nick_name", "");
        String age = spfRecord.getString("age", "");
        String city = spfRecord.getString("city", "");
        String gender = spfRecord.getString("gender", "");
        String school = spfRecord.getString("school", "");
        String birthDayTime = spfRecord.getString("birth_day_time", "");
        String sign = spfRecord.getString("sign", "");
        birthDay=birthDayTime;

//        etAccount.setText(account);
        etNickName.setText(nickName);
        etSchool.setText(school);
        etSign.setText(sign);
        tvBirthDayTime.setText(birthDayTime);


        if(TextUtils.equals("男",gender)){
            rbBoy.setChecked(true);
        }
        if(TextUtils.equals("女",gender)){
            rbGirl.setChecked(true);
        }


        for(int i=0;i<cities.length;i++)
        {
            if(TextUtils.equals(cities[i],city)){
                selectedCityPosition=i;
                break;
        }
        }
        spinnerCity.setSelection(selectedCityPosition);

    }

}





