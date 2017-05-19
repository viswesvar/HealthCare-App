package com.assignment.diabetesrecords.common.helper.sql_lite_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Calculator Free on 11/24/16.
 */
public class DBHelper extends SQLiteOpenHelper
{

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table diabetes_entry(diabetes_entry_id integer PRIMARY KEY AUTOINCREMENT,entry_date text,entry_time text,food_taken_status text,glucose_reading real,notes text)");

        db.execSQL("create table medicine_record(id integer PRIMARY KEY AUTOINCREMENT,entry_date text,entry_time text,food_taken_status text,title text,description text,insulineinformation text)");

        db.execSQL("create table doctor(id integer PRIMARY KEY AUTOINCREMENT,name text,phone text,emailid text)");

        db.execSQL("create table appointment(id integer PRIMARY KEY AUTOINCREMENT,doctor_id integer, doctor_name text, doctor_phone text, doctor_email text, appointment_date text,appointment_time text,appointment_reason text)");

        db.execSQL("create table my_profile(id integer PRIMARY KEY AUTOINCREMENT,first_name text,last_name text,middle_name text,dob text,diabetes_type text,gender text)");

        db.execSQL("create table reminder(id integer PRIMARY KEY AUTOINCREMENT, title text, description text, repeation_frequency text, reminder_date text,reminder_time text)");




        //db.execSQL("delete table category");
      //  db.execSQL("create table category(categoryid integer PRIMARY KEY AUTOINCREMENT,categoryname text)");
      //  db.execSQL("create table card(cardid integer PRIMARY KEY AUTOINCREMENT,businessname text,personname text,street text,city text,state text,lat real,lon real,contact1 text,contact2 text,email text,pic text,categoryid integer,country text,comment text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion)
    {


    }
}
