package com.aboundmediasolutions.services.hpoints;

/* Last revision: 1.0
 * @author - Benjamin Durham
 * @version - 1.0
 *
 * Questions, suggestions, concerns?
 * Contact: bdurhamk@gmail.com
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.aboundmediasolutions.services.hpoints.utilities.APITaskHandler;
import com.aboundmediasolutions.services.hpoints.utilities.Strategos;

import java.io.IOException;

public class MainActivity extends FragmentActivity implements SelectionScreenFragment.OnFragmentInteractionListener, APITaskHandler.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener, RecoverFragment.OnFragmentInteractionListener {

    private Strategos strategos = new Strategos();

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private RegisterFragment mRegisterFragment = new RegisterFragment();
    private SelectionScreenFragment mSelectionScreenFragment = new SelectionScreenFragment();
    private RecoverFragment mRecoverFragment = new RecoverFragment();

    private static final String TAG_ASYNC_FRAGMENT = "TAG_ASYNC_TASK_HANDLER_FRAGMENT";
    private APITaskHandler mAPITaskHandler;

    private static final String uri = "http://10.0.2.2/appbackend/index";
    // Changes with each version of the application/serverside
    public static final String APIKey = "test";
    // DEVELOPMENT ONLY: TAG for testing purposes, REMOVE PRIOR TO RELEASE, delete all Log.v's
    private static String TAG = "MainActivity";

    // DEVELOPMENT ONLY: Method for finding out the current thread being used
    public void whichThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.v("CurrentThread", "We're on the Main Thread Johnny boy!");
        } else {
            Log.v("CurrentThread", "This is not the main thread");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG,"OnCreate started...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // If no InstanceState is found; open SelectionScreenFragment so that user can choose next action
        if (savedInstanceState == null) {
            //mFragmentManager.beginTransaction().add(R.id.fragmentholder, mSelectionScreenFragment).commit();
            strategos.buildTransaction(this, mSelectionScreenFragment, "add", "SELECTION_SCREEN_FRAGMENT", 0);
        } else {
	        // Restore fragment from stored
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        }
        mAPITaskHandler = (APITaskHandler)mFragmentManager.findFragmentByTag(TAG_ASYNC_FRAGMENT);
        // If InstanceState is found;
        // If the tag for the AsyncTaskHandlerFragment is null then attach APITaskHandler which handles all network connections
        if (mAPITaskHandler == null) {
            mAPITaskHandler = new APITaskHandler();
            mFragmentManager.beginTransaction().add(mAPITaskHandler, TAG_ASYNC_FRAGMENT).commit();
            Log.v(TAG, "ASYNC NOW AVAILABLE");
        }
        Log.v(TAG,"OnCreate finished...");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean networkConnectionAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting() && connectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }



    public void changeActivity(Integer flags) {
        if(flags == 1) {
            Intent intent = new Intent(this, AdminHomeActivity.class);
            this.startActivity(intent);
        } else {
            Intent intent = new Intent(this, UserHomeActivity.class);
            this.startActivity(intent);
        }
    }

    @Override
    public void onPreExecute() {
       /* if (apiTaskHandlerList.size() == 0) {
            progressBar = (ProgressBar)findViewById(R.id.progressBar1);
            progressBar.findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);
        }
        apiTaskHandlerList.add(APITaskHandler.HttpTask); */
    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute(String result) {
        
    }

    // This method handles all "heavy lifting" and work associated with the SelectionScreenFragment and allows callbacks between the Fragment and host Activity
    @Override
    public void onSelectionScreenFragmentInteraction(View view) {
        switch(view.getId()) {
            case R.id.login_button :
                //TODO: Check if email and password EditText's have values, call AsyncTask class method for HTTP Post to login, if response is success go to appropriate activity, if false toast user to try again
                // Get text from all EditTexts, set them to Strings
                EditText mEmailEditText = (EditText)findViewById(R.id.email_edit_text);
                EditText mPasswordEditText = (EditText)findViewById(R.id.password_edit_text);

                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                // Check if all EditTexts have values
                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    Log.v(TAG, "email is " + email);
                    Log.v(TAG, "password is " + password);
                    //TODO: Grab AsyncTask, pass along password & email Strings and send them using HTTP GET request
                    if (networkConnectionAvailable()) {
                        // Async call to GET method
                        try {
                            mAPITaskHandler.loginUser(uri, APIKey, email, password);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Unable to make request", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Please check network connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please fill the required fields", Toast.LENGTH_SHORT).show();
                }
                Log.v(TAG, "login case over");
                break;
            case R.id.register_text_view :
                //mFragmentManager.beginTransaction().replace(R.id.fragmentholder, mRegisterFragment, "TAG_REGISTER_FRAGMENT").addToBackStack(null).commit();
                //String tag = "";
                strategos.buildTransaction(this, mRegisterFragment, "replace", "RECOVER_FRAGMENT", 1);
                Log.v(TAG, "register fragment added");
                this.whichThread();
                break;
            case R.id.recover_text_view :
                mFragmentManager.beginTransaction().replace(R.id.fragmentholder, mRecoverFragment, "TAG_RECOVER_FRAGMENT").addToBackStack(null).commit();
                Log.v(TAG, "recover fragment added");
                break;
        }
    }

    // This method handles all "heavy lifting" and work associated with the RegisterScreenFragment and allows callbacks between the Fragment and host Activity
    @Override
    public void onRegisterFragmentInteraction(View view) {
        EditText mEmailEditText = (EditText)findViewById(R.id.register_email_edit_text);
        EditText mNameEditText = (EditText)findViewById(R.id.register_name_edit_text);
        EditText mPasswordEditText = (EditText)findViewById(R.id.register_password_edit_text);
        EditText mVerifyPasswordEditText = (EditText)findViewById(R.id.register_password_verify_edit_text);
        switch(view.getId()) {
            case R.id.register_clear_button :
                // Clear all currently set text inside of EditTexts by iterating through all child EditTexts
                ViewGroup group = (ViewGroup)findViewById(R.id.register_fragment);
                for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                    View v = group.getChildAt(i);
                    if (v instanceof EditText) {
                        ((EditText)v).setText("");
                    }
                }
                break;
            case R.id.register_register_button :
                // Get values from all EditTexts, set them to Strings
                String email = mEmailEditText.getText().toString();
                String name = mNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String verifiedPassword = mVerifyPasswordEditText.getText().toString();
                // Check if all EditTexts have values
                if (email.trim().length() > 0 && name.trim().length() > 0 && password.trim().length() > 0 && verifiedPassword.trim().length() > 0) {
                    // Check if password is the same as verified password
                    if (password.equals(verifiedPassword)) {
                        // Check network connection, Take all String values and pass them to HTTPManager POST method
                        if (networkConnectionAvailable()) {
                            try {
                                Toast.makeText(this, "Making register request", Toast.LENGTH_SHORT).show();
                                // Pass values to registerUser method
                                mAPITaskHandler.registerUser(uri, APIKey, name, email, password);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(this, "Unable to register user, please try again", Toast.LENGTH_SHORT).show();
                            }
                            // Clear EditTexts, alert user to success or failure
                            Toast.makeText(this, "Account registered", Toast.LENGTH_LONG);


                        } else {
                        Toast.makeText(this, "Please check network connection", Toast.LENGTH_SHORT);
                        }
                    } else {
                        mPasswordEditText.setText("");
                        mVerifyPasswordEditText.setText("");
                        Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onRecoverFragmentInteraction(View view) {
        switch(view.getId()) {

        }
    }



}