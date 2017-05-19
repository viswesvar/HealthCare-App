package com.assignment.diabetesrecords.modules.my_profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.assignment.diabetesrecords.common.helper.sql_lite_helper.DBHelper;
import com.assignment.diabetesrecords.entity.MyProfile;

import java.util.ArrayList;

/**
 * Created by Calculator Free on 11/24/16.
 */
public class MyProfileManager

{
    DBHelper helper;
    SQLiteDatabase db;
    Context ctx;


    public MyProfileManager(Context ctx) {
        super();
        this.ctx = ctx;

        helper = new DBHelper(ctx, "DiabetesEntryDB",null,1);
    }


    public long insert(MyProfile obj)
    {
        long l = 0;

        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("first_name", obj.getFirstName());
        cv.put("last_name", obj.getLastName());
        cv.put("middle_name", obj.getMiddleName());
        cv.put("dob", obj.getDOB());

        cv.put("diabetes_type", obj.getDiabetesType());
        cv.put("gender", obj.getGender());

        l = db.insert("my_profile", null, cv);
        db.close();
        return l;
    }


    public long update(MyProfile obj)
    {
        long l = 0;

        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();


        cv.put("first_name", obj.getFirstName());
        cv.put("last_name", obj.getLastName());
        cv.put("middle_name", obj.getMiddleName());
        cv.put("dob", obj.getDOB());

        cv.put("diabetes_type", obj.getDiabetesType());
        cv.put("gender", obj.getGender());

        l = db.update("my_profile", cv,"id=?",new String[]{obj.getId()+""});
        db.close();
        return l;
    }


    public ArrayList<MyProfile> getAll()
    {
        db = helper.getReadableDatabase();

        Cursor cr = db.rawQuery("select * from my_profile", null);

        ArrayList<MyProfile> list = new ArrayList<MyProfile>();

        while(cr.moveToNext())
        {
            int Id= cr.getInt(0);
            String sFirstName= cr.getString(1);
            String sLastName= cr.getString(2);
            String sMiddleName= cr.getString(3);
            String sDOB= cr.getString(4);
            String sDiabetesType= cr.getString(5);
            String sGender= cr.getString(6);

            MyProfile myProfile = new MyProfile(Id, sFirstName,sLastName,sMiddleName,sDOB,sDiabetesType, sGender);
            list.add(myProfile);
        }

        db.close();
        return list;
    }



}

