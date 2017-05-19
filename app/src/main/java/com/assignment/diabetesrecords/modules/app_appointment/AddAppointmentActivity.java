package com.assignment.diabetesrecords.modules.app_appointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.activity.MainActivity;
import com.assignment.diabetesrecords.common.my_validator.MyValidator;
import com.assignment.diabetesrecords.common.permission_management_android6.PermissionUtil;
import com.assignment.diabetesrecords.common.push_appointments_to_calender.PushAppointmentsToCalender;
import com.assignment.diabetesrecords.entity.Appointment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.WRITE_CALENDAR;

public class AddAppointmentActivity extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {
    private EditText dateet, timeet, reasonet;
    private Button bSave;
    private int mDoctorId=0;
    private String mDoctorName="", mDoctorPhone="", mDoctorEmail="";

    private List<String> ALL_APP_PERMISSIONS= new ArrayList<String>();
    private View mLayout;
    private String sStartTime="", sStartDate="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent in= getIntent();
        mDoctorId = Integer.valueOf(in.getStringExtra("DoctorId"));
        mDoctorName = in.getStringExtra("DoctorName");
        mDoctorPhone = in.getStringExtra("DoctorPhone");
        mDoctorEmail= in.getStringExtra("DoctorEmail");


        dateet = (EditText) findViewById(R.id.dateet);
        timeet = (EditText) findViewById(R.id.timeet);
        reasonet = (EditText) findViewById(R.id.reasonet);

        dateet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            AddAppointmentActivity.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setAccentColor(getResources().getColor(R.color.colorAccent));
                    dpd.show(getFragmentManager(), "Datepickerdialog");
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
                             AddAppointmentActivity.this,
                            now.get(Calendar.HOUR),
                            now.get(Calendar.MINUTE),
                            false
                    );
                    tpd.setAccentColor(getResources().getColor(R.color.colorAccent));
                    tpd.show(getFragmentManager(), "Datepickerdialog");
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

        mLayout = findViewById(R.id.sample_main_layout);
        getPermissions();

    }

    private void getPermissions()
    {
        ALL_APP_PERMISSIONS.clear();
        ALL_APP_PERMISSIONS.add(READ_CALENDAR);
        ALL_APP_PERMISSIONS.add(WRITE_CALENDAR);



        if (PermissionUtil.AllPermissionGranted(ALL_APP_PERMISSIONS, AddAppointmentActivity.this))
        {

        }else
        {

            PermissionUtil.checkAndRequestPermissions(ALL_APP_PERMISSIONS, AddAppointmentActivity.this, AddAppointmentActivity.this, mLayout);
        }


    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String time = (hourOfDay>12?(hourOfDay-12):hourOfDay)+":"+minute+" "+(hourOfDay>12?"PM":"AM");
        timeet.setText(time);
        sStartTime=hourOfDay + ":" + minute;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year ;
        dateet.setText(date);
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


        if (timeet.length() ==0)
        {
            result=false;
            timeet.setError("Enter Time");
        }
        else
        {
            timeet.setError(null);
        }


        if (reasonet.length() ==0)
        {
            result=false;
            reasonet.setError("Enter Reason");
        }
        else
        {
            reasonet.setError(null);
        }






        return result;
    }

    private void save()
    {
        AppointmentManager manager= new AppointmentManager(this);
        long l=0;
        try{



            String sDate= MyValidator.getDateInyyyymmdd(dateet.getText().toString());
            String sTime = MyValidator.getTimeIn24HoursFormat(timeet.getText().toString());
            String sReason= reasonet.getText().toString();



            Appointment appointment = new Appointment(0,mDoctorId ,mDoctorName, mDoctorPhone, mDoctorEmail, sDate, sTime, sReason);


            l = manager.insert(appointment);


        }
        catch(Exception ex)
        {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }


        if(l>0) {


            Toast.makeText(this, "Record Save", Toast.LENGTH_SHORT).show();

            ALL_APP_PERMISSIONS.clear();
            ALL_APP_PERMISSIONS.add(READ_CALENDAR);
            ALL_APP_PERMISSIONS.add(WRITE_CALENDAR);



            if (PermissionUtil.AllPermissionGranted(ALL_APP_PERMISSIONS, AddAppointmentActivity.this))
            {
                addEventToCalendar();
            }else
            {

                PermissionUtil.checkAndRequestPermissions(ALL_APP_PERMISSIONS, AddAppointmentActivity.this, AddAppointmentActivity.this, mLayout);
            }


            Intent i= new Intent(AddAppointmentActivity.this, MainActivity.class);
            startActivity(i);

        }
        else
            Toast.makeText(this,"Record Not Save",Toast.LENGTH_SHORT).show();

    }

    private  long addEventToCalendar()
    {



        int iStatus=1;
        long calendar_event_id =0;

        Calendar cal = Calendar.getInstance();

        sStartDate = dateet.getText().toString();
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


        //long endDate = cal.getTimeInMillis(); //  + 90*60*1000; // System.currentTimeMillis();

        String sTitle = "Appointment with " + mDoctorName;

        calendar_event_id= PushAppointmentsToCalender.pushAppointmentsToCalender(AddAppointmentActivity.this, sTitle, sTitle, "",iStatus, startDate, startDate, true, false, 0, "" , false, false,"", "","","");

        return  calendar_event_id;
    }


}
