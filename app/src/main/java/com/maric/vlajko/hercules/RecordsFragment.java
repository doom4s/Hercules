package com.maric.vlajko.hercules;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;


/**
 * A simple {@link Fragment} subclass.

 */


public class RecordsFragment extends RoboFragment implements AdapterView.OnItemSelectedListener{
    //  TextView textView;
    Spinner spinner,mSpinner;
    ArrayList<String> years,months;
    ArrayAdapter<String> adapter,mAdapter;
    XYPlot plot;
    String[] monthArray;

    public RecordsFragment() {
        // Required empty public constructor
    }
    // @InjectView (R.id.button1)

  //  @InjectView (R.id.spinner)Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthArray = getResources().getStringArray(R.array.months);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);// prevents taking snapshots of screen



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_records, container, false);

        years = Database.getInstance(getActivity().getApplicationContext()).getYears();
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(years.size()!=0) {
            spinner = (Spinner) v.findViewById(R.id.spinner);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            spinner.setSelection(0);
        }
        if (spinner!=null){
           // months = new ArrayList<>();
                setMonth(spinner.getSelectedItem().toString());
                mSpinner = (Spinner) v.findViewById(R.id.spinner_months);
                mSpinner.setAdapter(mAdapter);
                mSpinner.setOnItemSelectedListener(this);
                mSpinner.setSelection(0);


        //    }
        }
        return v;
      //  relativeLayout = (RelativeLayout) v.findViewById(R.id.pager);



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        crateGraph(view, Integer.parseInt(spinner.getSelectedItem().toString()), Integer.parseInt(mSpinner.getSelectedItem().toString()));


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      //  if(view.equals(spinner)) {
        if(parent.getId()==spinner.getId()) {
            updateMonth(spinner.getSelectedItem().toString());

       }
        if(parent.getId()==mSpinner.getId())
        {
            Toast.makeText(getActivity().getApplicationContext(), "Selected <|> month", Toast.LENGTH_SHORT).show();
            plot.setTitle(monthArray[Integer.parseInt(mSpinner.getSelectedItem().toString())-1]);
            plot.redraw();
        }
       /* if(view.equals(mSpinner)){
            plot.setTitle(mSpinner.getSelectedItem().toString());
            plot.redraw();
        }*/
       // plot.setTitle(mSpinner.getSelectedItem().toString());
         //   Toast.makeText(getActivity(), "Selected <|> " + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

       // }
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void setMonth(String year){
        months = Database.getInstance(getActivity().getApplicationContext()).getMonths(year);
        mAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item, months);
        mAdapter.notifyDataSetChanged();


    }
    public void updateMonth(String year){
        months = Database.getInstance(getActivity().getApplicationContext()).getMonths(year);
        mAdapter.clear();
        mAdapter.addAll(months);
        mAdapter.notifyDataSetChanged();
       // plot.setTitle(mSpinner.getSelectedItem().toString());
       // plot.redraw();


    }

    public void crateGraph(View v,int year, int month){
        int iYear = year;
        int iMonth = month-1;
        int iDay = 1;

// Create a calendar object and set year and month
        Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);

// Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Toast.makeText(getActivity(),"Dani u mesecu: "+daysInMonth, Toast.LENGTH_SHORT).show();

        // initialize our XYPlot reference:
        plot = (XYPlot) v.findViewById(R.id.mySimpleXYPlot);
        // Create a couple arrays of y-values to plot:
        Number[] series1Numbers = {4, 5, 3, 8, 6, 10,4, 8, 3, 8, 2, 2 };
        Number[] series2Numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13 ,14 ,15 ,16 ,17 ,18 ,19 ,20, 21,22,23,24,25,26,27,28,29,30,31};

        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                getResources().getString(R.string.days));
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, getResources().getString(R.string.level));
        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.BLACK, Color.WHITE, Color.WHITE, null);


        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.BLACK, Color.WHITE, Color.WHITE, null);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(1);
        plot.setDomainLeftMin(1);
        plot.setDomainLeftMax(31);
        plot.setHorizontalScrollBarEnabled(true);
        plot.setTicksPerDomainLabel(1);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
        plot.setTitle(mSpinner.getSelectedItem().toString());

        plot.redraw();
    }
}

