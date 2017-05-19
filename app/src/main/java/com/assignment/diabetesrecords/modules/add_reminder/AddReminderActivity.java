package com.assignment.diabetesrecords.modules.add_reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.activity.MainActivity;
import com.assignment.diabetesrecords.common.my_validator.MyValidator;
import com.assignment.diabetesrecords.common.permission_management_android6.PermissionUtil;
import com.assignment.diabetesrecords.common.push_appointments_to_calender.PushAppointmentsToCalender;
import com.assignment.diabetesrecords.entity.Reminder;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.WRITE_CALENDAR;

public class AddReminderActivity extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {
    private EditText dateet, timeet, titleet, descriptionet, enddateet;
    private Button bSave, bAddAnother;
    private Spinner spRepeationFrequency;
    private List<String> ALL_APP_PERMISSIONS= new ArrayList<String>();
    private View mLayout;
    private String sStartTime="", sStartDate="", sStartEndDate="";
    private LinearLayout LLEndDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        dateet = (EditText) findViewById(R.id.dateet);
        timeet = (EditText) findViewById(R.id.timeet);
        titleet = (EditText) findViewById(R.id.titleet);
        descriptionet = (EditText) findViewById(R.id.descriptionet);
        enddateet = (EditText) findViewById(R.id.enddateet);
        LLEndDate = (LinearLayout) findViewById(R.id.LLEndDate);

        spRepeationFrequency= (Spinner) findViewById(R.id.spRepeationFrequency);


        //---------------------------------------


        //----------------------------------------------




        dateet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            AddReminderActivity.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setAccentColor(getResources().getColor(R.color.colorAccent));
                    dpd.show(getFragmentManager(), "StartDatepickerdialog");
                }
                return true;
            }
        });



        enddateet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd1 = DatePickerDialog.newInstance(
                            AddReminderActivity.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd1.setAccentColor(getResources().getColor(R.color.colorAccent));
                    dpd1.show(getFragmentManager(), "EndDatepickerdialog");
                }
                return true;
            }
        });

        timeet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar now = Calendar.getInstance();
                    TimePickerDialog tpd = TimePickerDialog.newInstance(
                             AddReminderActivity.this,
                            now.get(Calendar.HOUR),
                            now.get(Calendar.MINUTE),
                            false
                    );
                    tpd.setAccentColor(getResources().getColor(R.color.colorAccent));
                    tpd.show(getFragmentManager(), "TimeDatepickerdialog");

                }
                return true;
            }
        });


        bSave= (Button) findViewById(R.id.bSave);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation())
                {
                    save();
                }

            }
        });

        bAddAnother= (Button) findViewById(R.id.bAddAnother);
        bAddAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmptyForm();

            }
        });

        mLayout = findViewById(R.id.sample_main_layout);
        getPermissions();

    }

    private void getPermissions()
    {
        ALL_APP_PERMISSIONS.clear();
        ALL_APP_PERMISSIONS.add(READ_CALENDAR);
        ALL_APP_PERMISSIONS.add(WRITE_CALENDAR);



        if (PermissionUtil.AllPermissionGranted(ALL_APP_PERMISSIONS, AddReminderActivity.this))
        {

        }else
        {

            PermissionUtil.checkAndRequestPermissions(ALL_APP_PERMISSIONS, AddReminderActivity.this, AddReminderActivity.this, mLayout);
        }


    }

    private void EmptyForm()
    {

        titleet.setText("");
        descriptionet.setText("");
        dateet.setText("");
        enddateet.setText("");
        timeet.setText("");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String time = (hourOfDay>12?(hourOfDay-12):hourOfDay)+":"+minute+" "+(hourOfDay>12?"PM":"AM");
        timeet.setText(time);
        sStartTime=hourOfDay + ":" + minute;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if(view.getTag().equals("StartDatepickerdialog") )
        {
            String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year ;
            dateet.setText(date);
            enddateet.setText(date);
        }
        else  if(view.getTag().equals("EndDatepickerdialog"))
        {
            String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year ;
            enddateet.setText(date);
        }


    }

    private boolean validation()
    {
        boolean result = true;

        if (dateet.length() ==0)
        {
            result=false;
            dateet.setError("Enter Date");
        }
        else
        {
            dateet.setError(null);
        }


        if (enddateet.length() ==0)
        {
            result=false;
            enddateet.setError("Enter End Date");
        }
        else
        {
            enddateet.setError(null);
        }

        if (timeet.length() ==0)
        {
            result=false;
            timeet.setError("Enter Time");
        }
        else
        {
            timeet.setError(null);
        }


        if (titleet.length() ==0)
        {
            result=false;
            titleet.setError("Enter Title");
        }
        else
        {
            titleet.setError(null);
        }






        return result;
    }

    private void save()
    {
        ReminderManager manager= new ReminderManager(this);
        long l=0;
        try{



            String sDate= MyValidator.getDateInyyyymmdd(dateet.getText().toString());
            String sTime = MyValidator.getTimeIn24HoursFormat(timeet.getText().toString());
            String sTitle= titleet.getText().toString();
            String sDescription= descriptionet.getText().toString();
            String sRepeationFrequency= spRepeationFrequency.getSelectedItem().toString();


            Reminder reminder = new Reminder(0,sTitle ,sDescription, sRepeationFrequency, sDate, sTime);


            l = manager.insert(reminder);

            if(l>0)
            {
                ALL_APP_PERMISSIONS.clear();
                ALL_APP_PERMISSIONS.add(READ_CALENDAR);
                ALL_APP_PERMISSIONS.add(WRITE_CALENDAR);



                if (PermissionUtil.AllPermissionGranted(ALL_APP_PERMISSIONS, AddReminderActivity.this))
                {
                    addEventToCalendar();
                }else
                {

                    PermissionUtil.checkAndRequestPermissions(ALL_APP_PERMISSIONS, AddReminderActivity.this, AddReminderActivity.this, mLayout);
                }
            }


        }
        catch(Exception ex)
        {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }


        if(l>0) {
            Toast.makeText(this, "Record Save", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(AddReminderActivity.this, MainActivity.class);
            startActivity(i);

        }
        else
            Toast.makeText(this,"Record Not Save",Toast.LENGTH_SHORT).show();

    }


    private  long addEventToCalendar()
    {



        int iStatus=1;
        long calendar_event_id =0;
        String EndDateYYYYMMDD="";

        Calendar cal = Calendar.getInstance();

        sStartDate = dateet.getText().toString();
        sStartEndDate = enddateet.getText().toString();
      //  sStartTime= timeet.getText().toString();

        String [] arrDate = sStartDate.split("-");
        int yr=Integer.valueOf(arrDate[2].toString()) ;
        int month=Integer.valueOf(arrDate[1].toString())-1 ;
        int day=Integer.valueOf(arrDate[0].toString()) ;

        String [] arrTime = sStartTime.split(":");
        int hr=Integer.valueOf(arrTime[0].toString()) ;
        int mins=Integer.valueOf(arrTime[1].toString()) ;

        cal.set(yr, month, day, hr, mins);
        long startDate = cal.getTimeInMillis(); //  + 90*60*1000; // System.currentTimeMillis();

        String weekDayIn3Letter = cal.getTime().toString().substring(0,3);
        String weekDayIn2Letter = MyValidator.getWeekDay2Letter(weekDayIn3Letter);

        arrDate = sStartEndDate.split("-");
        yr=Integer.valueOf(arrDate[2].toString()) ;
        month=Integer.valueOf(arrDate[1].toString())-1 ;
        day=Integer.valueOf(arrDate[0].toString());
        cal.set(yr, month, day, hr, mins);
        long endDate = cal.getTimeInMillis(); //  + 90*60*1000; // System.currentTimeMillis();


        EndDateYYYYMMDD=MyValidator.getDateInYYYYMMDDFormat(sStartEndDate);

        String sRepeationFrequency= spRepeationFrequency.getSelectedItem().toString();
        boolean isDaily=false, isWeekly=false;
        if (sRepeationFrequency.equals("Daily"))
        {
            isDaily=true;
            isWeekly = false;
        }
        else if (sRepeationFrequency.equals("Weekly"))
        {
            isWeekly=true;
            isDaily=false;
        }





        calendar_event_id= PushAppointmentsToCalender.pushAppointmentsToCalender(AddReminderActivity.this, titleet.getText().toString(), descriptionet.getText().toString(), "",iStatus, startDate, endDate, true, false, 0, "", isDaily , isWeekly, EndDateYYYYMMDD,weekDayIn2Letter,"","" );

        return  calendar_event_id;
    }

}
