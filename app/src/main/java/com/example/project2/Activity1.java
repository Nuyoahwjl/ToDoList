package com.example.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.bean.Account;

public class Activity1 extends AppCompatActivity {

    private AccountOpenHelper mAccountOpenHelper;
    CheckBox rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        getSupportActionBar().setTitle("注册");

        Button btn=(Button)findViewById(R.id.button);
        EditText ac=findViewById(R.id.account);
        EditText ps1=findViewById(R.id.pass1);
        EditText ps2=findViewById(R.id.pass2);
        rb=findViewById(R.id.agree);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ac.getText().toString();
                String pas1=ps1.getText().toString();
                String pas2=ps2.getText().toString();
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(Activity1.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pas1))
                {
                    Toast.makeText(Activity1.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.equals(pas1, pas2))
                {
                    Toast.makeText(Activity1.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!rb.isChecked())
                {
                    Toast.makeText(Activity1.this, "请同意协议", Toast.LENGTH_SHORT).show();
                    return;
                }

                Account account=new Account();
                account.setUsername(name);
                account.setPassword(pas1);

                long row=mAccountOpenHelper.insertData(account);
                if(row!=-1){
                    Toast.makeText(Activity1.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Activity1.this.finish();
//                    Intent intent=new Intent(Activity1.this, ListActivity.class);
//                    startActivity(intent);
                }else{
                    Toast.makeText(Activity1.this, "注册失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    //隐式跳转
    //url
    public void to_other(View view){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

//    //相机
    public void to_other1(View view){
        Intent intent=new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

//    //相册
    public void to_other2(View view){
        Intent intent=new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("image/*");
        startActivity(intent);
    }

    //打电话
    public void to_other3(View view){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:15391560195"));
        startActivity(intent);
    }

//    //发短信
    public void to_other4(View view){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:15391560195"));
        intent.putExtra("sms_body","你好");
        startActivity(intent);
    }


    //弹出一个对话框
    public void popdia(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("此页面尚未完善，更多功能敬请期待");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("确认",null);
//        builder.create();
//        builder.show();
        AlertDialog al=builder.create();
        al.show();
    }

    public void popdia1(View view) {
        if(rb.isChecked()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("用户协议");
            builder.setMessage("你好，这只是一个ToDoList，请放心使用");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setPositiveButton("确认", null);
//        builder.create();
//        builder.show();
            AlertDialog al = builder.create();
            al.show();
        }
    }

}



