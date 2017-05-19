package com.assignment.diabetesrecords.modules.my_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.activity.MainActivity;
import com.assignment.diabetesrecords.common.my_validator.MyValidator;
import com.assignment.diabetesrecords.entity.MyProfile;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class MyProfileActivity extends AppCompatActivity  implements
        DatePickerDialog.OnDateSetListener {
    private EditText dateet,  firstnameet, middlenameet, lastnameet;
    private Spinner gender, type;
    private Button bSave;
    private ArrayList<MyProfile> myProfilesList;
    private int myProfileId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateet = (EditText) findViewById(R.id.dateet);

        firstnameet = (EditText) findViewById(R.id.firstnameet);
        middlenameet = (EditText) findViewById(R.id.middlenameet);
        lastnameet = (EditText) findViewById(R.id.lastnameet);

        gender= (Spinner) findViewById(R.id.gender);
        type= (Spinner) findViewById(R.id.type);

        dateet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            MyProfileActivity.this,
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



        bSave= (Button) findViewById(R.id.bSave);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation())
                {
                    if(myProfileId>0)
                    {
                        update();
                    }
                    else
                    {
                        save();
                    }


                }

            }
        });
        loadData();

    }

    private void loadData() {
        MyProfile myProfile;
        MyProfileManager manager = new MyProfileManager(this);
        myProfilesList = manager.getAll();
        if (myProfilesList.size() > 0)
        {

            myProfile = myProfilesList.get(0);
            myProfileId = myProfile.getId();
            dateet.setText(myProfile.getDOB().toString());
            firstnameet.setText(myProfile.getFirstName().toString());
            middlenameet.setText(myProfile.getMiddleName().toString());
            lastnameet.setText(myProfile.getLastName().toString());

            String[] gender_arrays= getResources().getStringArray(R.array.gender_arrays);

            for(int i=0; i<gender_arrays.length; i++)
            {
                if(gender_arrays[i].equals(myProfile.getGender().toString()))
                {
                    gender.setSelection(i);
                }
            }


            String[] type_arrays= getResources().getStringArray(R.array.type_arrays);

            for(int i=0; i<type_arrays.length; i++)
            {
                if(type_arrays[i].equals(myProfile.getDiabetesType().toString()))
                {
                    type.setSelection(i);
                }
            }


        }
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
            dateet.setError("Enter DOB");
        }
        else
        {
            dateet.setError(null);
        }


        if (firstnameet.length() ==0)
        {
            result=false;
            firstnameet.setError("Enter First Name");
        }
        else
        {
            firstnameet.setError(null);
        }

        if (lastnameet.length() ==0)
        {
            result=false;
            lastnameet.setError("Enter Last Name");
        }
        else
        {
            lastnameet.setError(null);
        }




        return result;
    }

    private void save()
    {
        MyProfileManager manager= new MyProfileManager(this);
        long l=0;
        try{



            String sDate= MyValidator.getDateInyyyymmdd(dateet.getText().toString());
            String sFirstName= firstnameet.getText().toString();
            String sLastName= lastnameet.getText().toString();
            String sMiddleName= middlenameet.getText().toString();
            String sDiabetesType= type.getSelectedItem().toString();
            String sGender= gender.getSelectedItem().toString();


            MyProfile profile = new MyProfile(0,sFirstName ,sLastName, sMiddleName, sDate, sDiabetesType, sGender);


            l = manager.insert(profile);


        }
        catch(Exception ex)
        {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }


        if(l>0) {
            Toast.makeText(this, "Record Save", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(MyProfileActivity.this, MainActivity.class);
            startActivity(i);

        }
        else
            Toast.makeText(this,"Record Not Save",Toast.LENGTH_SHORT).show();

    }


    private void update()
    {
        MyProfileManager manager= new MyProfileManager(this);
        long l=0;
        try{



            String sDate= MyValidator.getDateInyyyymmdd(dateet.getText().toString());
            String sFirstName= firstnameet.getText().toString();
            String sLastName= lastnameet.getText().toString();
            String sMiddleName= middlenameet.getText().toString();
            String sDiabetesType= type.getSelectedItem().toString();
            String sGender= gender.getSelectedItem().toString();


            MyProfile profile = new MyProfile(myProfileId,sFirstName ,sLastName, sMiddleName, sDate, sDiabetesType, sGender);


            l = manager.update(profile);


        }
        catch(Exception ex)
        {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }


        if(l>0) {
            Toast.makeText(this, "Record Save", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(MyProfileActivity.this, MainActivity.class);
            startActivity(i);

        }
        else
            Toast.makeText(this,"Record Not Save",Toast.LENGTH_SHORT).show();

    }

}
