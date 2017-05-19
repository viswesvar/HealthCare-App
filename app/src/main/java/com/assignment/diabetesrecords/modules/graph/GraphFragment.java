package com.assignment.diabetesrecords.modules.graph;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.entity.DiabetesEntry;
import com.assignment.diabetesrecords.modules.diabetes_entry.EntryManager;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class GraphFragment extends Fragment{
    int iPageCounter;
    ArrayList<DiabetesEntry> diabetesEntryList;

    public GraphFragment() {
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
        View parent= inflater.inflate(R.layout.fragment_graph, container, false);

        //====Beging Fetch Data and Prepare Data for Graph=======================
        iPageCounter=1;
        EntryManager entryManager= new EntryManager(getActivity());
        diabetesEntryList = entryManager.getAllForGraph(iPageCounter);

        String dateval="";
        Float val;
        DataPoint dp;
        LineGraphSeries<DataPoint> series= new LineGraphSeries<DataPoint>();
        DiabetesEntry diabetesEntry;
        int iSize=0, iDataPoints=0;
        if(diabetesEntryList.size() > 0)
        {
            iSize = diabetesEntryList.size()-1;

            for(int i=0; i<=iSize ; i++)
            {
                diabetesEntry = diabetesEntryList.get(iSize-i);
                if(!dateval.equals(diabetesEntry.getEntryDate()) )
                {
                    dateval = diabetesEntry.getEntryDate();
                    val = diabetesEntry.getGlucoseReading();

                    //-------
                    //  String target = "Thu Sep 28 20:29:30 JST 2000";
                    // SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
                    Date result;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    try {
                        result = df.parse(dateval);
                        dp= new DataPoint(result, val);
                        iDataPoints +=1;
                        series.appendData(dp,true,iDataPoints, false);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //------------
                }



            }
            //====End Fetch Data and Prepare data for graph=========================
        }
        else
        {
            iSize = diabetesEntryList.size();
        }



        GraphView graph = (GraphView) parent.findViewById(R.id.graph);
/*

        // generate Dates
        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();


        // you can directly pass Date objects to DataPoint-Constructor
// this will convert the Date to double via Date#getTime()
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d1, 1),
                new DataPoint(d2, 5),
                new DataPoint(d3, 3)
        });

        */

        graph.addSeries(series);
// set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(iDataPoints); // only 4 because of the space

        // / graph.getGridLabelRenderer().setNumHorizontalLabels(diabetesEntryList.size()); // only 4 because of the space

// set manual x bounds to have nice steps
//        graph.getViewport().setMinX(d1.getTime());
  //      graph.getViewport().setMaxX(d3.getTime());

        graph.getViewport().setMinX(series.getLowestValueX());
        graph.getViewport().setMaxX(series.getHighestValueX());

        graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);
        return parent;
    }

}
