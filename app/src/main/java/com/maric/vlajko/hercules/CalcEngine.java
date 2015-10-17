package com.maric.vlajko.hercules;

import android.os.Bundle;

import com.google.inject.Singleton;

import java.util.Observable;

/**
 * Created by Vlajko on 9/5/2015.
 */
@Singleton
public class CalcEngine extends Observable{
    private float b1; //Ukupan broj jedinica
    private float b2; //Ciljana glikemija
    private float b3; //UH faktor 1j
    private float b4; //Glikemija pre jela
    private float b5; //Planirani unos UH u gr
    private float b6; //Aktivni insulin
    private float b7; //Bazal j/h u trenutku davanja
    private float a1; //indeks insulinske osetljivosti
    private float a2; //Glikemija iznad ciljane
    private float a3; //Korekcija
    private float a4; //Jedinica ako je normalna glikemija
    private float a5; //Ukupna kolicina insulina za obrok

    public float getA1() {
        if(b1!=0) {
            this.a1 = 100 / b1;
            return this.a1;
        }
        else{
            return 0;
        }
    }


    public float getA2() {
        this.a2 = b4-b2;
        return this.a2;
    }

    public float getA3() {
        this.a3 = (getA2()/getA1())-(b6+b7);
        return this.a3;
    }

    public float getA4() {
        this.a4 = b5/b3;
        return a4;
    }

    public float getA5(){
        this.a5 = a4+a3;
        return a5;
    }

    public void setA5(float a5){
        this.a5 = a5;
    }



    public void setA4(float a4) {
        this.a4 = a4;
    }





    public void setA3(float a3) {
        this.a3 = a3;
    }

    public float getB7() {
        return b7;
    }

    public void setB7(float b7) {
        this.b7 = b7;
        setChanged();
        Bundle bundle = new Bundle();
        bundle.putFloat("B7",b7);
        notifyObservers(bundle);
    }


    public float getB9() {
        return b9;
    }

    public void setB9(float b9) {
        this.b9 = b9;
    }

    private float b9 = 0;


    public void setA2(float a2) {
        this.a2 = a2;
    }



    public float getB1() {
        return b1;
    }

    public void setB1(float b1) {
        this.b1 = b1;
        setChanged();
        Bundle bundle = new Bundle();
        bundle.putFloat("B1",b1);
        notifyObservers(bundle);
    }

    public float getB2() {
        return b2;
    }

    public void setB2(float b2) {
        this.b2 = b2;
        setChanged();
        Bundle bundle = new Bundle();
        bundle.putFloat("B2",b2);
        notifyObservers(bundle);
    }

    public float getB3() {
        return b3;
    }

    public void setB3(float b3) {
        this.b3 = b3;
        setChanged();
        Bundle bundle = new Bundle();
        bundle.putFloat("B3",b3);
        notifyObservers(bundle);
    }

    public float getB4() {
        return b4;
    }

    public void setB4(float b4) {
        this.b4 = b4;
        setChanged();
        Bundle bundle = new Bundle();
        bundle.putFloat("B4",b4);
        notifyObservers(bundle);
    }

    public float getB5() {
        return b5;
    }

    public void setB5(float b5) {
        this.b5 = b5;
        setChanged();
        Bundle bundle = new Bundle();
        bundle.putFloat("B5",b5);
        notifyObservers(bundle);
    }

    public float getB6() {
        return b6;
    }

    public void setB6(float b6) {
        this.b6 = b6;
        setChanged();
        Bundle bundle = new Bundle();
        bundle.putFloat("B6",b6);
        notifyObservers(bundle);
    }




}
