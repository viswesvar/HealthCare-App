package com.assignment.diabetesrecords.modules.add_reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.assignment.diabetesrecords.common.helper.sql_lite_helper.DBHelper;
import com.assignment.diabetesrecords.entity.Reminder;

/**
 * Created by Calculator Free on 11/24/16.
 */
public class ReminderManager

{
    DBHelper helper;
    SQLiteDatabase db;
    Context ctx;

    public ReminderManager(Context ctx) {
        super();
        this.ctx = ctx;

        helper = new DBHelper(ctx, "DiabetesEntryDB",null,1);
    }


    public long insert(Reminder obj)
    {
        long l = 0;

        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("title", obj.getTitle());
        cv.put("description", obj.getDescription());
        cv.put("repeation_frequency", obj.getRepeation_frequency());

        cv.put("reminder_date", obj.getReminder_date());
        cv.put("reminder_time", obj.getReminder_time());


        l = db.insert("reminder", null, cv);
        db.close();
        return l;
    }









}

