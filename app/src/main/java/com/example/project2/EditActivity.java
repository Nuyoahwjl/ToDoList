package com.example.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.bean.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private Note note;
    private EditText etTitle,etContent;
    private NoteDbOpenHelper mNoteDbOpenHelper;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle=findViewById(R.id.et_title);
        etContent=findViewById(R.id.et_content);
        mNoteDbOpenHelper=new NoteDbOpenHelper(this);
        initdata();
    }

    //从主页拿到数据
    private void initdata() {
        Intent intent =getIntent();
        note = (Note)intent.getSerializableExtra("note");
        if(note!=null){
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            time=note.getCreatedTime();
        }
    }

    //通过id保存
    public void save(View view) {
        String title=etTitle.getText().toString();
        String content=etContent.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        note.setTitle(title);
        note.setContent(content);
        note.setCreatedTime(getCurrentTime());

        long rowId=mNoteDbOpenHelper.updateData(note);

        if(rowId>=0){
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            this.finish();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd日 HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }


   //通过id删除
    public void delete(View view) {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("温馨提示");
            builder.setMessage("\n确认要删除吗");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    long rowId=mNoteDbOpenHelper.deleteData(note);
                    if(rowId>=0){
                        Toast.makeText(EditActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        EditActivity.this.finish();
                    }else{
                        Toast.makeText(EditActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            AlertDialog al=builder.create();
            al.show();


//            long rowId=mNoteDbOpenHelper.deleteData(note);
//        if(rowId>=0){
//            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
//            this.finish();
//        }else{
//            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
//        }

    }
}




    //通过时间保存
//    public void save(View view) {
//        String title=etTitle.getText().toString();
//        String content=etContent.getText().toString();
//        if(TextUtils.isEmpty(title)){
//            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Note note1=new Note();
//        note1.setTitle(title);
//        note1.setContent(content);
//        note1.setCreatedTime(getCurrentTime());
//
//        time=note.getCreatedTime();
//        long rowId=mNoteDbOpenHelper.updateData(note1,time);
//        if(rowId>=0){
//            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
//            this.finish();
//        }else{
//            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
//        }
//    }