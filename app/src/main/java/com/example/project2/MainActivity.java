package com.example.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_REGISTER = 1;
    private Button btnToast;
    private TextView text1;
    private TextView text2;
    private CheckBox ch1;
    String username="王家乐";
    String phone="15391560195";
    String password_="wjl051224";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       btnToast=findViewById(R.id.btn_login);
       text1=findViewById(R.id.tex1);
       text2=findViewById(R.id.tex2);
       ch1=findViewById(R.id.checkbox1);

       //记住密码
       initdata();

        btnToast.setOnClickListener(v -> {
            String s=text1.getText().toString();
            String p=text2.getText().toString();
            if(TextUtils.isEmpty(username))
            {
                Toast.makeText(MainActivity.this,"还没有注册账号",Toast.LENGTH_SHORT).show();
                return;
            }
            if((TextUtils.equals(s,username)||TextUtils.equals(s,phone))&&TextUtils.equals(p,password_))
            {
                Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                if(ch1.isChecked()){
                    SharedPreferences spf=getSharedPreferences("spf1",MODE_PRIVATE);
                    SharedPreferences.Editor edit=spf.edit();
                    edit.putString("account",s);
                    edit.putString("password",p);
                    edit.putBoolean("isremember",true);
                    edit.apply();
                }
                else{
                    SharedPreferences spf=getSharedPreferences("spf1",MODE_PRIVATE);
                    SharedPreferences.Editor edit=spf.edit();
                    edit.putBoolean("isremember",false);
                    edit.apply();
                }
                Intent intent =new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("account",s);
                startActivity(intent);
                //跳转过去就销毁该页面
                MainActivity.this.finish();

            }
            else
            {
                Toast.makeText(MainActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initdata() {
        SharedPreferences spf=getSharedPreferences("spf1",MODE_PRIVATE);
        boolean isRemember=spf.getBoolean("isremember",false);
        String account=spf.getString("account","");
        String password=spf.getString("password","");
//        username=account;
//        password_=password;

        if(isRemember){
            text1.setText(account);
            text2.setText(password);
            ch1.setChecked(true);
        }

    }

    //显式跳转


    public  void jump(View view){
        Intent intent=new Intent(MainActivity.this, Activity1.class);
//参数回传
          startActivity(intent);
//        startActivityForResult(intent, REQUEST_CODE_REGISTER);
//          this.finish();

    }
//        @Override
//        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//            if (resultCode == REQUEST_CODE_REGISTER && resultCode == Activity1.RE && data != null) {
//                Bundle extras = data.getExtras();
//                String account = extras.getString("account", "");
//                String password = extras.getString("password", "");
//                username = account;
//                password_ = password;
//                text1.setText(account);
//                text2.setText(password);
//
//            }
//        }

}