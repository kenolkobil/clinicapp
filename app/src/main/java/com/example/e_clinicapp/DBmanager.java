package com.example.e_clinicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmanager extends SQLiteOpenHelper
{
    private static final String dbname="clinic.db";
    private SQLiteDatabase db;

    public DBmanager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String qry="create table patients(id integer primary key autoincrement,surname text,lastname text,Email text,phone integer,nationalid integer,patientid integer,date text)";
         db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS patients");
        onCreate(db);

    }
    public String addRecord(String p1,String p2,String p3,String p4,String p5,String p6,String p7){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("surname",p1);
        cv.put("lastname",p2);
        cv.put("Email",p3);
        cv.put("phone",p4);
        cv.put("nationalid",p5);
        cv.put("patientid",p6);
        cv.put("date",p7);
       long res= db.insert("patients",null,cv);
       if (res==-1)
           return "failed";
       else
           return "succesfully registred";
    }
}
