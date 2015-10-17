package com.maric.vlajko.hercules;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;

import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * Created by Vlajko on 9/4/2015.
 */
public class B1Fragment extends RoboDialogFragment{

    private AppActivity appActivity;
    private HomeFragment homeFragment;
    @InjectView  (R.id.b1EditText)
    private EditText editText;
    @InjectView (R.id.b1Button)
    private Button button;
    @InjectView(R.id.textViewFragment)
    private TextView textView;
    private String text;
    private int buttonNo;
    @Inject
    CalcEngine calcEngine;
    AppActivity apPactivity;
    HomeFragment fragment;
    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setStyle(STYLE_NO_TITLE, 0);
        bundle = new Bundle();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.b1_fragment_layout, container, false);
        buttonNo = getArguments().getInt("buttonNumber");
        getDialog().setTitle(getArguments().getString("Title"));
        return v;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
     //   apPactivity = (AppActivity) activity;
     //   bundle = new Bundle();
      //  fragment = (HomeFragment)((AppActivity) activity).getSupportFragmentManager().getFragment(bundle, "home");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragment = (HomeFragment) getActivity().getSupportFragmentManager().getFragment(bundle, "home");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().isEmpty()){
                    switch (buttonNo){
                        case 1:
                            calcEngine.setB1(Float.parseFloat(editText.getText().toString()));
                          //  fragment.onButtonPressedB1(Float.parseFloat(editText.getText().toString()));
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(calcEngine.getB1()),Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            calcEngine.setB2(Float.parseFloat(editText.getText().toString()));
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(calcEngine.getB2()),Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            calcEngine.setB3(Float.parseFloat(editText.getText().toString()));
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(calcEngine.getB3()),Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            calcEngine.setB4(Float.parseFloat(editText.getText().toString()));
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(calcEngine.getB4()),Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            calcEngine.setB5(Float.parseFloat(editText.getText().toString()));
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(calcEngine.getB5()),Toast.LENGTH_SHORT).show();
                            break;
                        case 6:
                            calcEngine.setB6(Float.parseFloat(editText.getText().toString()));
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(calcEngine.getB6()),Toast.LENGTH_SHORT).show();
                            break;
                        case 7:
                            calcEngine.setB7(Float.parseFloat(editText.getText().toString()));
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(calcEngine.getB7()),Toast.LENGTH_SHORT).show();
                            break;
                    }
                    //Toast.makeText(getActivity(), "Setted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "You entered null", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
        textView.setText(text);
    }
    public void setRequestText(String text){
        this.text = text;
    }
}
