package com.assignment.diabetesrecords.modules.diabetes_entry;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.modules.medicine.MedicineFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EntriesOptionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EntriesOptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntriesOptionsFragment extends Fragment {

    private Button bMedicineEntry,bGlucoseEntry;
    View bGlucoseEntry1, bMedicineEntry1;
    FrameLayout FRAGMENT_PLACEHOLDER;

    public static Context mContext;
    public static AppCompatActivity mParentActivity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static EntriesOptionsFragment newInstance(Context context)
    {
        EntriesOptionsFragment fragment = new EntriesOptionsFragment();

        mContext = context;
        mParentActivity = (AppCompatActivity) context;

        return fragment;
    }

    public EntriesOptionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntriesOptionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntriesOptionsFragment newInstance(String param1, String param2) {
        EntriesOptionsFragment fragment = new EntriesOptionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private void loadingTimeView()
    {
        bGlucoseEntry1.setVisibility(View.VISIBLE);
        bMedicineEntry1.setVisibility(View.INVISIBLE);

        //--------------------------------------------------------
        FragmentManager childFragMan = getChildFragmentManager();
        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
        EntriesFragment fragB = EntriesFragment.newInstance(mParentActivity);
        //childFragTrans.
       // childFragTrans.add(R.id.FRAGMENT_PLACEHOLDER, fragB);
        childFragTrans.replace(R.id.FRAGMENT_PLACEHOLDER, fragB);
        childFragTrans.addToBackStack("B");
        childFragTrans.commit();
        //------------------------------------------------------------


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent= inflater.inflate(R.layout.fragment_entries_options, container, false);

        bMedicineEntry= (Button) parent.findViewById(R.id.bMedicineEntry);
        bGlucoseEntry= (Button) parent.findViewById(R.id.bGlucoseEntry);

        FRAGMENT_PLACEHOLDER = (FrameLayout) parent.findViewById(R.id.FRAGMENT_PLACEHOLDER);

        bMedicineEntry1 = (View) parent.findViewById(R.id.bMedicineEntry1);
        bGlucoseEntry1 = (View) parent.findViewById(R.id.bGlucoseEntry1);

        //-----------------------------------------------

        loadingTimeView();
        //-----------------------------------------


        bMedicineEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bGlucoseEntry1.setVisibility(View.INVISIBLE);
                bMedicineEntry1.setVisibility(View.VISIBLE);

                //--------------------------------------------------------
                FragmentManager childFragMan = getChildFragmentManager();
                FragmentTransaction childFragTrans = childFragMan.beginTransaction();
                MedicineFragment fragB = MedicineFragment.newInstance(mParentActivity);
                childFragTrans.replace(R.id.FRAGMENT_PLACEHOLDER, fragB);
                childFragTrans.addToBackStack("B");
                childFragTrans.commit();
                //------------------------------------------------------------

            }
        });


        bGlucoseEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bGlucoseEntry1.setVisibility(View.VISIBLE);
                bMedicineEntry1.setVisibility(View.INVISIBLE);

                //--------------------------------------------------------
                FragmentManager childFragMan = getChildFragmentManager();
                FragmentTransaction childFragTrans = childFragMan.beginTransaction();
               // EntriesFragment fragB = new EntriesFragment();
                EntriesFragment fragB = EntriesFragment.newInstance(mParentActivity);
                childFragTrans.replace(R.id.FRAGMENT_PLACEHOLDER, fragB);
                childFragTrans.addToBackStack("B");
                childFragTrans.commit();
                //------------------------------------------------------------

            }
        });


        return  parent;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
