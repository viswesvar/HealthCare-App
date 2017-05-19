package com.assignment.diabetesrecords.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.assignment.diabetesrecords.R;


public class FoodExerciseFragment extends Fragment{

    Button bFood, bExercise;
    TextView tvFood, tvExercise;

    public FoodExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent= inflater.inflate(R.layout.fragment_food_exercise, container, false);
        tvFood = (TextView) parent.findViewById(R.id.tvFood);
        tvExercise = (TextView) parent.findViewById(R.id.tvExercise);

        bFood = (Button) parent.findViewById(R.id.bFood);
        bFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFood.setVisibility(View.VISIBLE);
                tvExercise.setVisibility(View.GONE);
            }
        });

        bExercise = (Button) parent.findViewById(R.id.bExercise);
        bExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvExercise.setVisibility(View.VISIBLE);
                tvFood.setVisibility(View.GONE);
            }
        });

        return parent;
    }

}
