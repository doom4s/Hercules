package com.maric.vlajko.hercules;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import roboguice.activity.RoboActionBarActivity;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {
    @InjectView(R.id.login_button) LoginButton button;

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            Profile profile = Profile.getCurrentProfile();
            User.getInstance().setName(profile.getName());
            User.getInstance().setFName(profile.getFirstName());
            User.getInstance().setlName(profile.getLastName());
            User.getInstance().setName(profile.getName());
            User.getInstance().setToken(profile.getId());
            Database.getInstance(getApplicationContext()).addUser();

            welcomeMessage();


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
    CallbackManager callbackManager;
    @InjectView (R.id.hello_text)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        button = (LoginButton) findViewById(R.id.login_button);
        button.setReadPermissions("public_profile","user_photos");
        button.registerCallback(callbackManager, callback);



    }

    @Override
    protected void onResume() {
        super.onResume();
        welcomeMessage();
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(String name, final Context context, AttributeSet attrs) {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.maric.vlajko.hercules", PackageManager.GET_SIGNATURES);
            for(android.content.pm.Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                System.out.println("KeyHash: "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch(PackageManager.NameNotFoundException | NoSuchAlgorithmException e){

        }
        FacebookSdk.sdkInitialize(getApplicationContext());





        return super.onCreateView(name, context, attrs);
        //  Intent intent = new Intent(this, AppActivity.class);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void welcomeMessage(){
        Profile profile = Profile.getCurrentProfile();
        if(profile!=null){
          //  Toast.makeText(getApplicationContext(), profile.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), AppActivity.class);
            startActivity(intent); // odkomentarisati kasnije
        }
    }


}
