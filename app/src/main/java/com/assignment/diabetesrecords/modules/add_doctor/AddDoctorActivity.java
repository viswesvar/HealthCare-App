package com.assignment.diabetesrecords.modules.add_doctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.common.my_validator.MyValidator;
import com.assignment.diabetesrecords.entity.Doctor;

public class AddDoctorActivity extends AppCompatActivity {
    View parent;
    private EditText nameet, phoneet, emailidet;
    private Button bSave, bAddAnother;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameet = (EditText) findViewById(R.id.nameet);
        phoneet = (EditText) findViewById(R.id.phoneet);
        emailidet = (EditText) findViewById(R.id.emailidet);

        bSave= (Button) findViewById(R.id.bSave);
        bAddAnother= (Button) findViewById(R.id.bAddAnother);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation())
                {
                    save();
                }

            }
        });


        bAddAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmptyForm();

            }
        });


    }

    private void EmptyForm()
    {

        nameet.setText("");
        phoneet.setText("");
        emailidet.setText("");

    }


    private boolean validation()
    {
        boolean result = true;

        if (nameet.length() ==0)
        {
            result=false;
            nameet.setError("Enter Name");
        }
        else
        {
            nameet.setError(null);
        }


        if (phoneet.length() ==0)
        {
            result=false;
            phoneet.setError("Enter Phone");
        }
        else
        {
            phoneet.setError(null);
        }




        if (emailidet.length() ==0)
        {
            result=false;
            emailidet.setError("Enter Email");
        }
        else
        {
            if(!MyValidator.isValidEmaillId(emailidet.getText().toString()))
            {
                result=false;
                emailidet.setError("Invalid Email");
            }
            else
            {
                emailidet.setError(null);
            }


        }




        return result;
    }

    private void save()
    {
        DoctorManager manager= new DoctorManager(this);
        long l=0;
        try{



            String sName= nameet.getText().toString();
            String sPhone= phoneet.getText().toString();
            String sEmail= emailidet.getText().toString();

            Doctor doctor = new Doctor(0, sName, sPhone, sEmail);


            l = manager.insert(doctor);


        }
        catch(Exception ex)
        {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }


        if(l>0) {
            Toast.makeText(this, "Record Save", Toast.LENGTH_SHORT).show();
            EmptyForm();
            //  ((MainActivity) mContext).refreshTabs();
            //finish();
        }
        else
            Toast.makeText(this,"Record Not Save",Toast.LENGTH_SHORT).show();

    }

}
