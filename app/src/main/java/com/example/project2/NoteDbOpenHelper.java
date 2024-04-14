package com.example.project2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project2.bean.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDbOpenHelper extends SQLiteOpenHelper {


    private static final String DB_NAME="noteSQLite.db";
    private static final String TABLE_NAME_NOTE="note";
    private static final String CREATE_TABLE_SQL="create table "+TABLE_NAME_NOTE+"(id integer primary key autoincrement,title text,content text,create_time text)";





    public NoteDbOpenHelper (Context context){
        super(context,DB_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(Note note){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreatedTime());

        return db.insert(TABLE_NAME_NOTE, null, values);
    }

    public List<Note> queryAllFromDb() {
        SQLiteDatabase db = getWritableDatabase();
        List<Note> notelist = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME_NOTE, null, null, null, null, null, null);

        if(cursor!=null){
            while(cursor.moveToNext()){
//                @SuppressLint("Range") String id= cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") int id= cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title= cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content= cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String createTime= cursor.getString(cursor.getColumnIndex("create_time"));

                Note note=new Note();
                note.setId(id);
                note.setTitle(title);
                note.setContent(content);
                note.setCreatedTime(createTime);

                notelist.add(note);

            }
            cursor.close();
        }
        return notelist;
    }


    public long updateData(Note note){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreatedTime());


        String []s={String.valueOf(note.getId())};
        return db.update(TABLE_NAME_NOTE, values, "id = ?",s);

    }

    public long deleteData(Note note) {
        SQLiteDatabase db=getWritableDatabase();
//        String []s= new String[]{note2.getCreatedTime()};
//        return db.delete(TABLE_NAME_NOTE,  "create_time = ?", s);
        String []s= new String[]{String.valueOf(note.getId())};
        return db.delete(TABLE_NAME_NOTE,  "id = ?", s);
    }
}




//通过时间保存
//public long updateData(Note note,String time){
//    SQLiteDatabase db=getWritableDatabase();
//    ContentValues values=new ContentValues();
//
//    values.put("title", note.getTitle());
//    values.put("content", note.getContent());
//    values.put("create_time", note.getCreatedTime());
//
//
//        String []s= new String[]{time};
//        return db.update(TABLE_NAME_NOTE, values, "create_time = ?", s);
//
//
//}
