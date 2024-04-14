package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project2.bean.Account;

public class AccountOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="accountSQLite.db";
    private static final String TABLE_NAME_ACCOUNT="account";
    private static final String CREATE_TABLE_SQL="create table "+TABLE_NAME_ACCOUNT+"(id integer primary key autoincrement,username text,password text)";


    public AccountOpenHelper (Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long insertData(Account account) {
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());

        return db.insert(TABLE_NAME_ACCOUNT, null, values);
    }
}
