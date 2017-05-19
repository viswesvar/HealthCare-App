package com.assignment.diabetesrecords.modules.medicine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.common.my_validator.MyValidator;
import com.assignment.diabetesrecords.entity.MedicineRecord;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;


public class MedicineFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {


    View parent;
    private EditText dateet, timeet, etTitle, etDescription, etInsulineInformation;
    private Button bSave, bAddAnother;
    Spinner spReadingTimings;



    public static Context mContext;
    public static AppCompatActivity mParentActivity;
    public MedicineFragment() {
        // Required empty public constructor

    }

    public static MedicineFragment newInstance(Context context)
    {
        MedicineFragment fragment = new MedicineFragment();

        mContext = context;
        mParentActivity = (AppCompatActivity) context;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent= inflater.inflate(R.layout.fragment_medicine, container, false);
        dateet = (EditText) parent.findViewById(R.id.dateet);
        timeet = (EditText) parent.findViewById(R.id.timeet);

        etTitle = (EditText) parent.findViewById(R.id.etTitle);
        etDescription = (EditText) parent.findViewById(R.id.etDescription);
        etInsulineInformation = (EditText) parent.findViewById(R.id.etInsulineInformation);

        bSave= (Button) parent.findViewById(R.id.bSave);
        bAddAnother= (Button) parent.findViewById(R.id.bAddAnother);
        spReadingTimings= (Spinner) parent.findViewById(R.id.spReadingTimings);





        dateet.setText(MyValidator.getCurrentDateInDDMMYYYY());
        timeet.setText(MyValidator.getCurrentTimeInAMPMFormat());

        dateet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            MedicineFragment.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setAccentColor(getResources().getColor(R.color.colorAccent));
                    dpd.show(mParentActivity.getFragmentManager(), "Datepickerdialog");
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
                            MedicineFragment.this,
                            now.get(Calendar.HOUR),
                            now.get(Calendar.MINUTE),
                            false
                    );
                    tpd.setAccentColor(getResources().getColor(R.color.colorAccent));
                    tpd.show(mParentActivity.getFragmentManager(), "Datepickerdialog");
                }
                return true;
            }
        });

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


        return parent;
    }

    private void EmptyForm()
    {

        etTitle.setText("");
        etDescription.setText("");
        etInsulineInformation.setText("");
        dateet.setText("");
        timeet.setText("");
        dateet.setText(MyValidator.getCurrentDateInDDMMYYYY());
        timeet.setText(MyValidator.getCurrentTimeInAMPMFormat());
    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String time = (hourOfDay>12?(hourOfDay-12):hourOfDay)+":"+minute+" "+(hourOfDay>12?"PM":"AM");
        timeet.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year ;
        dateet.setText(date);
    }

    private boolean validation()
    {
        boolean result = true;

        if (etTitle.length() ==0)
        {
            result=false;
            etTitle.setError("Enter Title");
        }
        else
        {
            etTitle.setError(null);
        }


        if (etInsulineInformation.length() ==0)
        {
            result=false;
            etInsulineInformation.setError("Enter Insuline Information");
        }
        else
        {
            etInsulineInformation.setError(null);
        }


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





        return result;
    }

    private void save()
    {
        MedicineManager medicineManager= new MedicineManager(getActivity());
        long l=0;
        try{

            String sDate= MyValidator.getDateInyyyymmdd(dateet.getText().toString());
            String sTime = MyValidator.getTimeIn24HoursFormat(timeet.getText().toString());


            String FoodTakenStatus= spReadingTimings.getSelectedItem().toString();

            String sTitle= etTitle.getText().toString();
            String sDescription= etDescription.getText().toString();
            String sInsulineInformation= etInsulineInformation.getText().toString();

            MedicineRecord medicineRecord = new MedicineRecord(0, sDate,sTime,FoodTakenStatus,sTitle, sDescription, sInsulineInformation);


            l = medicineManager.insertMedicine(medicineRecord);


        }
        catch(Exception ex)
        {
            Toast.makeText(mContext, ex.toString(), Toast.LENGTH_SHORT).show();
        }


        if(l>0) {
            Toast.makeText(mContext, "Record Save", Toast.LENGTH_SHORT).show();
            EmptyForm();
          //  ((MainActivity) mContext).refreshTabs();
            //finish();
        }
        else
            Toast.makeText(mContext,"Record Not Save",Toast.LENGTH_SHORT).show();

    }

}
