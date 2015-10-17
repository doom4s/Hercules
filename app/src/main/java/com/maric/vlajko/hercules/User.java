package com.maric.vlajko.hercules;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.inject.Singleton;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Vlajko on 5/27/2015.
 */
public class User {

    private int id;
    private String fName;
    private String lName;
    private String name;
    private String password;
    private String token;
    private Bitmap userImage;
    private static User instance = null;

    public static User getInstance() {
        if (instance == null) {
            //    Toast.makeText(cxt.getApplicationContext(), "Pravi novu instancu", Toast.LENGTH_SHORT).show();
            instance = new User();
        }
        return instance;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getFName() {return fName;}
    public void setFName(String name) {this.fName = name;}
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getToken(){return token;}
    public void setToken(String token){this.token = token;}
    public Bitmap getUserImage() {return userImage;}
    public void setUserImage(Bitmap userImage) {this.userImage = userImage;}

    public static Bitmap getFacebookProfilePicture(String userID) throws IOException {
        URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=small");
        Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

        return bitmap;
    }






}
