package com.example.project2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.bean.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private EditText etTitle,etContent;
    private NoteDbOpenHelper mNoteDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle=findViewById(R.id.et_title);
        etContent=findViewById(R.id.et_content);
        mNoteDbOpenHelper=new NoteDbOpenHelper(this);

    }

    public void add(View view) {
        String title=etTitle.getText().toString();
        String content=etContent.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Note note=new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setCreatedTime(getCurrentTime());

        long row=mNoteDbOpenHelper.insertData(note);
        if(row!=-1){
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        this.finish();
        }else{
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd日 HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }

}
