package com.assignment.diabetesrecords.modules.app_appointment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.assignment.diabetesrecords.common.helper.sql_lite_helper.DBHelper;
import com.assignment.diabetesrecords.entity.Appointment;

/**
 * Created by Calculator Free on 11/24/16.
 */
public class AppointmentManager

{
    DBHelper helper;
    SQLiteDatabase db;
    Context ctx;

    public AppointmentManager(Context ctx) {
        super();
        this.ctx = ctx;

        helper = new DBHelper(ctx, "DiabetesEntryDB",null,1);
    }


    public long insert(Appointment obj)
    {
        long l = 0;

        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("doctor_id", obj.getDoctorId());
        cv.put("doctor_name", obj.getDoctorName());
        cv.put("doctor_phone", obj.getDoctorPhone());
        cv.put("doctor_email", obj.getDoctorEmail());

        cv.put("appointment_date", obj.getAppointmentDate());
        cv.put("appointment_time", obj.getAppointmentTime());
        cv.put("appointment_reason", obj.getAppointmentReason());


        l = db.insert("appointment", null, cv);
        db.close();
        return l;
    }










}

