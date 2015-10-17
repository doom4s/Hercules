package com.maric.vlajko.hercules;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends RoboFragment implements Observer {

    @InjectView(R.id.firstButton)
    Button firstButton;
    @InjectView(R.id.secondButton)
    Button secondButton;
    @InjectView(R.id.thirdButton)
    Button thirdButton;
    @InjectView(R.id.fourthButton)
    Button fourthButton;
    @InjectView(R.id.fifthButton)
    Button fifthButton;
    @InjectView(R.id.sixthButton)
    Button sixthButton;
    @InjectView(R.id.seventhButton)
    Button seventhButton;
    @InjectView(R.id.textField1)
    TextView textView1;
    @InjectView(R.id.textField2)
    TextView textView2;
    @InjectView(R.id.textField3)
    TextView textView3;
    @InjectView(R.id.textField4)
    TextView textView4;
    @InjectView(R.id.textField5)
    TextView textView5;
    @InjectView(R.id.textField6)
    TextView textView6;
    @InjectView(R.id.textField7)
    TextView textView7;
    @InjectView(R.id.calculate)
    Button calcButton;
    @Inject
    CalcEngine calcEngine;
    Bundle bundle;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override//data is ready but view isn't
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override//view is ready
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setTargetFragment(this, getTargetRequestCode());
        if(savedInstanceState!=null){
            textView1.setText(savedInstanceState.getString("Text1"));
            textView2.setText(savedInstanceState.getString("Text2"));
            textView3.setText(savedInstanceState.getString("Text3"));
            textView4.setText(savedInstanceState.getString("Text4"));
            textView5.setText(savedInstanceState.getString("Text5"));
            textView6.setText(savedInstanceState.getString("Text6"));
            textView7.setText(savedInstanceState.getString("Text7"));
        }
        super.onViewCreated(view, savedInstanceState);
        firstButton.setText(getResources().getString(R.string.buton1text));
        secondButton.setText(getResources().getString(R.string.buton2text));
        thirdButton.setText(getResources().getString(R.string.buton3text));
        fourthButton.setText(getResources().getString(R.string.buton4text));
        fifthButton.setText(getResources().getString(R.string.buton5text));
        sixthButton.setText(getResources().getString(R.string.buton6text));
        seventhButton.setText(getResources().getString(R.string.buton7text));
        calcEngine.addObserver(this);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Kliknuto", Toast.LENGTH_SHORT).show();
                B1Fragment b1Fragment = new B1Fragment();
                bundle.putInt("buttonNumber", 1);
                bundle.putString("Title", getString(R.string.buton1text));
                b1Fragment.setArguments(bundle);
                b1Fragment.show(getFragmentManager(), "mojDijalog");

            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Kliknuto", Toast.LENGTH_SHORT).show();
                B1Fragment b1Fragment = new B1Fragment();
                bundle.putInt("buttonNumber", 2);
                bundle.putString("Title", getString(R.string.buton2text));
                b1Fragment.setArguments(bundle);
                b1Fragment.show(getFragmentManager(), "mojDijalog");

            }
        });
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Kliknuto", Toast.LENGTH_SHORT).show();
                B1Fragment b1Fragment = new B1Fragment();
                bundle.putInt("buttonNumber", 3);
                bundle.putString("Title", getString(R.string.buton3text));
                b1Fragment.setArguments(bundle);
                b1Fragment.show(getFragmentManager(), "mojDijalog");

            }
        });
        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Kliknuto", Toast.LENGTH_SHORT).show();
                B1Fragment b1Fragment = new B1Fragment();
                bundle.putInt("buttonNumber", 4);
                bundle.putString("Title", getString(R.string.buton4text));
                b1Fragment.setArguments(bundle);
                b1Fragment.show(getFragmentManager(), "mojDijalog");

            }
        });
        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Kliknuto", Toast.LENGTH_SHORT).show();
                B1Fragment b1Fragment = new B1Fragment();
                bundle.putInt("buttonNumber", 5);
                bundle.putString("Title", getString(R.string.buton5text));
                b1Fragment.setArguments(bundle);
                b1Fragment.show(getFragmentManager(), "mojDijalog");

            }
        });
        sixthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Kliknuto", Toast.LENGTH_SHORT).show();
                B1Fragment b1Fragment = new B1Fragment();
                bundle.putInt("buttonNumber", 6);
                bundle.putString("Title", getString(R.string.buton6text));
                b1Fragment.setArguments(bundle);
                b1Fragment.show(getFragmentManager(), "mojDijalog");

            }
        });
        seventhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Kliknuto", Toast.LENGTH_SHORT).show();
                B1Fragment b1Fragment = new B1Fragment();
                bundle.putInt("buttonNumber", 7);
                bundle.putString("Title", getString(R.string.buton7text));
                b1Fragment.setArguments(bundle);
                b1Fragment.show(getFragmentManager(), "mojDijalog");
            }
        });

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Toast.makeText(getActivity().getApplicationContext(), "Kliknuto", Toast.LENGTH_SHORT).show();
                Database.getInstance(getActivity().getApplicationContext()).addRecord(
                        getArguments().get("token").toString(),
                        calcEngine.getA1(),
                        calcEngine.getA2(),
                        calcEngine.getA3(),
                        calcEngine.getA4(),
                        calcEngine.getA5());

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Text1", textView1.getText().toString());
        outState.putString("Text2", textView2.getText().toString());
        outState.putString("Text3", textView3.getText().toString());
        outState.putString("Text4", textView4.getText().toString());
        outState.putString("Text5", textView5.getText().toString());
        outState.putString("Text6", textView6.getText().toString());
        outState.putString("Text7", textView7.getText().toString());

    }

    public BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    @Override
    public void update(Observable observable, Object data) {
        Bundle bundle = (Bundle) data;
        if(((Bundle) data).containsKey("B1")) {
            textView1.setText("" + round(((Bundle) data).getFloat("B1"), 2));
            textView1.setTextColor(Color.parseColor("#558B2F"));
            firstButton.getBackground().setAlpha(95);
        }
        if(((Bundle) data).containsKey("B2")) {
            textView2.setText("" + round(((Bundle) data).getFloat("B2"), 2));
            textView2.setTextColor(Color.parseColor("#558B2F"));
            secondButton.getBackground().setAlpha(95);}

        if(((Bundle) data).containsKey("B3")) {
            textView3.setText("" + round(((Bundle) data).getFloat("B3"), 2));
            textView3.setTextColor(Color.parseColor("#558B2F"));
            thirdButton.getBackground().setAlpha(95);
        }
        if(((Bundle) data).containsKey("B4")) {
            textView4.setText("" + round(((Bundle) data).getFloat("B4"), 2));
            textView4.setTextColor(Color.parseColor("#558B2F"));
            fourthButton.getBackground().setAlpha(95);
        }
        if(((Bundle) data).containsKey("B5")) {
            textView5.setText("" + round(((Bundle) data).getFloat("B5"), 2));
            textView5.setTextColor(Color.parseColor("#558B2F"));
            fifthButton.getBackground().setAlpha(95);
        }
        if(((Bundle) data).containsKey("B6")) {
            textView6.setText("" + round(((Bundle) data).getFloat("B6"), 2));
            textView6.setTextColor(Color.parseColor("#558B2F"));
            sixthButton.getBackground().setAlpha(95);
        }
        if(((Bundle) data).containsKey("B7")) {
            textView7.setText("" + round(((Bundle) data).getFloat("B7"), 2));
            textView7.setTextColor(Color.parseColor("#558B2F"));
            seventhButton.getBackground().setAlpha(95);
        }

    }
}
