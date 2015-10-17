package com.maric.vlajko.hercules;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.inject.Inject;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_app)
public class AppActivity extends RoboActionBarActivity implements AdapterView.OnItemClickListener {



    @InjectView(R.id.drawerLayout)
    private DrawerLayout drawerLayout;
    @InjectView(R.id.login_button)
    private LoginButton loginButton;
    @InjectView(R.id.userName)
    private TextView userName;
    @InjectView(R.id.profileImage)
    private ImageView profileImage;
    @InjectView(R.id.coverImage)
    private ImageView coverImage;
    @InjectView(R.id.drawerlist)
    private LinearLayout drawer;
    @InjectView(R.id.optionList)
    private ListView optionList;
    private ArrayList<String> options;
    private Bundle bundle;
    @Inject
    private CalcEngine calcEngine;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private String coverPhotoID;
    private int id;
    private ActionBarDrawerToggle drawerToggle;
    private ProfilePictureView profilePictureView;
    private ActionBar actionBar;
    private Profile profile;


    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            setDetails();


        }

        @Override
        public void onCancel() {

            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(FacebookException error) {

            Toast.makeText(getApplicationContext(), "Error jebi ga", Toast.LENGTH_SHORT).show();
        }
    };
    private android.support.v4.app.FragmentTransaction transaction;
    private FragmentManager manager;

    @Override
    protected void onResume() {
        super.onResume();
        setDetails();
    }

    public void setDetails(){
        profile = Profile.getCurrentProfile();
        User.getInstance().setName(profile.getName());
        User.getInstance().setFName(profile.getFirstName());
        User.getInstance().setlName(profile.getLastName());
        User.getInstance().setName(profile.getName());
        User.getInstance().setToken(profile.getId());
        bundle.putString("token",profile.getId());
        Picasso.with(getApplicationContext()).load("https://graph.facebook.com/" + profile.getId()+ "/picture?type=large").into(profileImage);
       // Picasso.with(getApplicationContext()).load("https://graph.facebook.com/" + profile.getId() + "...?fields=cover&access_token=" + AccessToken.getCurrentAccessToken()).into(coverImage);
      //  Picasso.with(getApplicationContext()).load("drawable/user_image.png").into(coverImage);
        userName.setText(profile.getName());

    }
    public void clearDetails(){
        userName.setText("unknown");
        profileImage.setImageDrawable(getDrawable(R.drawable.user_image));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(null);
        bundle = new Bundle();
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        options = new ArrayList<String>();

        options.add(getString(R.string.home));
        options.add(getString(R.string.history));
        options.add(getString(R.string.settings));
        optionList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, options);
        optionList.setAdapter(adapter);
        optionList.setOnItemClickListener(this);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        loadSelection(0);




        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile");
        loginButton.registerCallback(callbackManager, callback);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    //User logged out
                   // clearDetails();
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/albums",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        JSONArray jArray;

                        try {
                      //  Log.d("Albums: ", response.getRawResponse());
                        JSONObject json = response.getJSONObject();
                        jArray = json.getJSONArray("data");

                        Log.d("Lenght: ", String.valueOf(jArray.length()));


                            for(int i=0; i<jArray.length(); i++){
                                JSONObject json_data = jArray.getJSONObject(i);
                                if(json_data.getString("name").equals("Cover Photos")){
                                    id = json_data.getInt("id");
                                    coverPhotoID = String.valueOf(json_data.getInt("id"));
                                    Toast.makeText(getApplicationContext(), json_data.getString("name")+"\n"+json_data.getInt("id"), Toast.LENGTH_SHORT).show();
                                 //   Picasso.with(getApplicationContext()).load("https://graph.facebook.com/" + id + "/photos&access_token="+AccessToken.getCurrentAccessToken()).into(profileImage);

                                  //  graph.facebook.com**the_album_id**/photos&access_token=_your_access_token‌​_.


                                }
                                Log.i("MyLOG", "created_time: " + json_data.getString("created_time") +
                                                "name: " + json_data.getString("name") +
                                                "id: " + json_data.getInt("id")

                                );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




            /* handle the result */
                    }
                }
        ).executeAsync();
// set Fragmentclass Arguments

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
        drawerLayout.closeDrawer(drawer);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    public void loadSelection(int i){
    optionList.setItemChecked(i, true);

        switch (i){
            case 0:

                transaction = manager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);
                transaction.replace(R.id.fragmentholder, homeFragment, "home").commit();
                break;
            case 1:
                transaction = manager.beginTransaction();
                RecordsFragment recordsFragment = new RecordsFragment();
                transaction.replace(R.id.fragmentholder, recordsFragment, "records").commit();
                break;
        }

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
       // drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == android.R.id.home){
                if(drawerLayout.isDrawerOpen(drawer)){
                    drawerLayout.closeDrawer(drawer);
                }else{
                    drawerLayout.openDrawer(drawer);
                }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(String name, final Context context, AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);

    }
    public BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }


}
