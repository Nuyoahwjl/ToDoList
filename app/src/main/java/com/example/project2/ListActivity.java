package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.adapter.MyAdapter;
import com.example.project2.bean.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mBtnAdd;
    private List<Note> mNotes;//数据项
    private MyAdapter mMyAdapter;//适配器
    private NoteDbOpenHelper mNoteDbOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        initView();
        initData();
        initEvent();

    }

    @Override
    protected void onResume(){
        super.onResume();
//        initView();
//        initData();
//        initEvent();
        refreshData();

    }

    private void refreshData() {
        mNotes=getDataFromDb();
        mMyAdapter.refresh(mNotes);
    }


    private void initEvent() {
        mMyAdapter=new MyAdapter(this,mNotes);
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        mNotes=new ArrayList<>();
        mNoteDbOpenHelper=new NoteDbOpenHelper(this);
//        for(int i=0;i<10;i++) {
//            Note note = new Note();
//            note.setTitle("标题"+i);
//            note.setContent("内容");
//            note.setCreatedTime(getCurrentTime());
//            mNotes.add(note);
//        }

        mNotes=getDataFromDb();

    }

    private List<Note> getDataFromDb() {
        return mNoteDbOpenHelper.queryAllFromDb();
    }

    private String getCurrentTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY年MM月dd HH:mm:ss");
        Date date=new Date();
        return simpleDateFormat.format(date);
    }


    private void initView() {
        mRecyclerView=findViewById(R.id.rlv);
    }


    public void personfile(View view){
        Intent intent=new Intent(ListActivity.this, Activity2.class);
        startActivity(intent);
//        this.finish();
    }

    public void add(View view){
        Intent intent=new Intent(ListActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent=new Intent(this,MainActivity.class);
        ListActivity.this.finish();
        startActivity(intent);
    }

}