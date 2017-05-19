package com.assignment.diabetesrecords.modules.summary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.activity.MainActivity;
import com.assignment.diabetesrecords.entity.DiabetesEntry;
import com.assignment.diabetesrecords.entity.MedicineRecord;
import com.assignment.diabetesrecords.modules.diabetes_entry.EntryManager;
import com.assignment.diabetesrecords.modules.medicine.MedicineManager;

import java.util.ArrayList;


public class SummaryFragment extends Fragment{

    ImageView ivCallEntryTab,ivCallMedicineTab;
    public static Context mContext;
    public static AppCompatActivity mParentActivity;

    int iPageCounter;
    ArrayList<DiabetesEntry> diabetesEntryList;
    DiabetesEntryAdapter diabetesEntryAdapter;
    private RecyclerView mRecyclerView;


    ArrayList<MedicineRecord> medicineList;
    MedicineAdapter medicineAdapter;
    private RecyclerView mRecyclerViewMedicine;
//fragment
    public SummaryFragment() {
        // Required empty public constructor
        int i=0;

    }

    public static SummaryFragment newInstance(Context context)
    {
        SummaryFragment fragment = new SummaryFragment();

        mContext = context;
        mParentActivity = (AppCompatActivity) context;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_summary, container, false);

        ivCallEntryTab = (ImageView) parent.findViewById(R.id.ivCallEntryTab);
        ivCallEntryTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).callEntryTab();
            }
        });

        ivCallMedicineTab= (ImageView) parent.findViewById(R.id.ivCallMedicineTab);
        ivCallMedicineTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).callMedicineTab();
            }
        });
        //-------------------------------------
        //ArrayList<Category> clist;
        //Begin -- Load Data in List--------------------------------
        EntryManager  entryManager= new EntryManager(getActivity());
        mRecyclerView = (RecyclerView) parent.findViewById(R.id.listUsers);


        iPageCounter=1;
        diabetesEntryList = entryManager.getAll(iPageCounter);
        diabetesEntryAdapter = new DiabetesEntryAdapter(getActivity(), diabetesEntryList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(diabetesEntryAdapter);

        //End -- Load Data in List--------------------------------



        //Begin -- Load Data in List--------------------------------
        MedicineManager medicineManager= new MedicineManager(getActivity());
        mRecyclerViewMedicine = (RecyclerView) parent.findViewById(R.id.list_medicine);

        iPageCounter=1;
        medicineList = medicineManager.getAll(iPageCounter);
        medicineAdapter = new MedicineAdapter(getActivity(), medicineList);


        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mRecyclerViewMedicine.setLayoutManager(mLayoutManager1);
        mRecyclerViewMedicine.setAdapter(medicineAdapter);

        //End -- Load Data in List--------------------------------
        //------------------------------------------

        return  parent;
    }

    @Override
    public void onResume() {
        super.onResume();
        EntryManager  entryManager= new EntryManager(getActivity());
        //ArrayList<Category> clist;
        //Begin -- Load Data in List--------------------------------

        iPageCounter=1;
        diabetesEntryList = entryManager.getAll(iPageCounter);
        diabetesEntryAdapter = new DiabetesEntryAdapter(getActivity(), diabetesEntryList);
        mRecyclerView.setAdapter(diabetesEntryAdapter);
        diabetesEntryAdapter.notifyDataSetChanged();


        //Begin -- Load Data in List--------------------------------
        MedicineManager medicineManager= new MedicineManager(getActivity());
        iPageCounter=1;
        medicineList = medicineManager.getAll(iPageCounter);
        medicineAdapter = new MedicineAdapter(getActivity(), medicineList);
        mRecyclerViewMedicine.setAdapter(medicineAdapter);
        medicineAdapter.notifyDataSetChanged();
    }
}
