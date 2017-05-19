package com.assignment.diabetesrecords.modules.diabetes_entry;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.common.my_validator.MyValidator;
import com.assignment.diabetesrecords.entity.DiabetesEntry;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;


public class EntriesFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {


    View parent;
    private EditText dateet, timeet, etReading, etNote;
    private Button bSave, bAddAnother;
    Spinner spReadingTimings;
    LinearLayout LLContainer, LLGlucoseEntry;
    private  int iHrs=0;






    public static Context mContext;
    public static AppCompatActivity mParentActivity;
    public EntriesFragment() {
        // Required empty public constructor

    }

    public static EntriesFragment newInstance(Context context)
    {
        EntriesFragment fragment = new EntriesFragment();

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
        parent= inflater.inflate(R.layout.fragment_entries, container, false);
        dateet = (EditText) parent.findViewById(R.id.dateet);
        timeet = (EditText) parent.findViewById(R.id.timeet);
        etReading = (EditText) parent.findViewById(R.id.etReading);
        etNote = (EditText) parent.findViewById(R.id.etNote);
        bSave= (Button) parent.findViewById(R.id.bSave);
        bAddAnother= (Button) parent.findViewById(R.id.bAddAnother);
        spReadingTimings= (Spinner) parent.findViewById(R.id.spReadingTimings);
        LLContainer = (LinearLayout) parent.findViewById(R.id.LLContainer);
        LLGlucoseEntry= (LinearLayout) parent.findViewById(R.id.LLGlucoseEntry);

//-------------------------------------


        dateet.setText(MyValidator.getCurrentDateInDDMMYYYY());
        timeet.setText(MyValidator.getCurrentTimeInAMPMFormat());

        String strTime= timeet.getText().toString().substring(0,timeet.getText().toString().indexOf(":"));
        String strAMPM = timeet.getText().toString().substring(timeet.getText().toString().indexOf(" ")+1);
        iHrs =Integer.valueOf(strTime) ;
        if(strAMPM.equals("PM"))
        {
            iHrs +=12;
        }
        //---------------------------------


        dateet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            EntriesFragment.this,
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
                            EntriesFragment.this,
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
        etReading.setText("");
        etNote.setText("");
        dateet.setText("");
        timeet.setText("");
        dateet.setText(MyValidator.getCurrentDateInDDMMYYYY());
        timeet.setText(MyValidator.getCurrentTimeInAMPMFormat());
        iHrs =0;

    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String time = (hourOfDay>12?(hourOfDay-12):hourOfDay)+":"+minute+" "+(hourOfDay>12?"PM":"AM");
        iHrs = hourOfDay;

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

        if (etReading.length() ==0)
        {
            result=false;
            etReading.setError("Enter Reading");
        }
        else
        {
            int val= Integer.valueOf(etReading.getText().toString());
            if((val<50) || (val > 350))
            {
                result=false;
                etReading.setError("Enter Correct Reading");
            }
            else
            {
                etReading.setError(null);
            }

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



        if(validateReadingTimings() == false)
        {
            result=false;
        }


        return result;
    }

    private boolean validateReadingTimings()
    {
        boolean result=false;
        String FoodTakenStatus= spReadingTimings.getSelectedItem().toString();
        if(FoodTakenStatus.indexOf("Breakfast")>0)
        {
            result = MyValidator.isBreakfastTime(iHrs);
            if(result==false)
            Toast.makeText(getActivity(), "Invalid Breakfast time" , Toast.LENGTH_LONG).show();

        }
        else if(FoodTakenStatus.indexOf("Lunch")>0)
        {
            result = MyValidator.isLunchTime(iHrs);

            if(result==false)
            Toast.makeText(getActivity(), "Invalid Lunch time" , Toast.LENGTH_LONG).show();
        } if(FoodTakenStatus.indexOf("Dinner")>0)
        {
            result = MyValidator.isDinnerTime(iHrs);

            if(result==false)
            Toast.makeText(getActivity(), "Invalid Dinner time" , Toast.LENGTH_LONG).show();
        }
        return result;
    }

    private void save()
    {
        EntryManager entryManager= new EntryManager(getActivity());
        long l=0;
        try{

            String sDate= MyValidator.getDateInyyyymmdd(dateet.getText().toString());
            String sTime = MyValidator.getTimeIn24HoursFormat(timeet.getText().toString());


            String FoodTakenStatus= spReadingTimings.getSelectedItem().toString();
            float GlucoseReading= Float.parseFloat(etReading.getText().toString());
            String Notes= etNote.getText().toString();


            DiabetesEntry diabetesEntry = new DiabetesEntry(0, sDate,sTime,FoodTakenStatus,GlucoseReading,Notes);


            l = entryManager.insert(diabetesEntry);


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
