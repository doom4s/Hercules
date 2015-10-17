package com.maric.vlajko.hercules;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.inject.Inject;

import java.math.BigDecimal;

import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * Created by Vlajko on 9/5/2015.
 */
public class ResultFragment  extends RoboDialogFragment {

    @Inject CalcEngine calcEngine;
    @InjectView(R.id.textResult1)
    TextView textView1;
    @InjectView(R.id.textResult2)
    TextView textView2;
    @InjectView(R.id.textResult3)
    TextView textView3;
    @InjectView(R.id.textResult4)
    TextView textView4;
    @InjectView(R.id.textResult5)
    TextView textView5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.result_layout, container ,false);
        getDialog().setTitle("Result");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView1.setText("ind.ins.oset. " + round(calcEngine.getA1(), 3));
        textView2.setText("Glik. iznad "+ round(calcEngine.getA2(), 3));
        textView3.setText("Korekcija " + round(calcEngine.getA3(), 3));
        textView4.setText("Jed. norm. glik. "+ round(calcEngine.getA4(),3));
        textView5.setText("Sum kol. ins. za obrok " + round(calcEngine.getA5(), 3));
      //  SELECT DISTINCT column FROM table;
    }

    public BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
}
