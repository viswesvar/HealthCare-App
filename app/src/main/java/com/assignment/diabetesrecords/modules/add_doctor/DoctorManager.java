package com.assignment.diabetesrecords.modules.add_doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.assignment.diabetesrecords.common.helper.sql_lite_helper.DBHelper;
import com.assignment.diabetesrecords.entity.Doctor;

import java.util.ArrayList;

/**
 * Created by Calculator Free on 11/24/16.
 */
public class DoctorManager

{
    DBHelper helper;
    SQLiteDatabase db;
    Context ctx;

    public DoctorManager(Context ctx) {
        super();
        this.ctx = ctx;

        helper = new DBHelper(ctx, "DiabetesEntryDB",null,1);
    }


    public long insert(Doctor record)
    {
        long l = 0;

        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("name", record.getName());
        cv.put("phone", record.getPhone());
        cv.put("emailid", record.getEmailid());

        l = db.insert("doctor", null, cv);
        db.close();
        return l;
    }




    public ArrayList<Doctor> getAll(int iPageCount)
    {
        db = helper.getReadableDatabase();
        int iOffset=0;
        int iLimit=10;
        iOffset=iLimit*(iPageCount)-iLimit;
        Cursor cr = db.rawQuery("select * from doctor order by id desc LIMIT " + iLimit + " OFFSET " +iOffset, null);
//        Cursor cr = db.rawQuery("select * from diabetes_entry order by diabetes_entry_id desc, entry_date asc LIMIT " + iLimit + " OFFSET " +iOffset, null);

        ArrayList<Doctor> list = new ArrayList<Doctor>();

        while(cr.moveToNext())
        {
            int Id= cr.getInt(0);
            String sName= cr.getString(1);
            String sPhone= cr.getString(2);
            String sEmail= cr.getString(3);

            Doctor doctor = new Doctor(Id, sName,sPhone,sEmail);
            list.add(doctor);
        }

        db.close();
        return list;
    }






}

