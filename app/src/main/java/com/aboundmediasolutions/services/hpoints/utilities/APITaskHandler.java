package com.aboundmediasolutions.services.hpoints.utilities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aboundmediasolutions.services.hpoints.R;
import com.aboundmediasolutions.services.hpoints.models.RequestPackage;
import com.aboundmediasolutions.services.hpoints.models.RewardData;
import com.aboundmediasolutions.services.hpoints.models.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class APITaskHandler extends Fragment {

    private ProgressBar progressBar;

    List<HttpTask> apiTask;
    List<UserData> userDataList;
    List<RewardData> rewardDataList;

    /**
     * Callback interface through which the fragment will report the
     * task's progress and results back to the Activity.
     */
    private OnFragmentInteractionListener mCallbacks;

    /**
     * Hold a reference to the parent Activity so we can report the
     * task's current progress and results. The Android framework
     * will pass us a reference to the newly created Activity after
     * each configuration change.
     */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Communicator");
        }
    }

    /**
     * This method will only be called once when the retained
     * Fragment is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
        apiTask = new ArrayList<>();
    }

    // Override onCreateView to initialize all of our views
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.api_task_handler_fragment, container, false);
        // initialize UI components including buttons, pass them to onSelectionFragmentListener
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);

        return view;
    }

    /**
     * Set the callback to null so we don't accidentally leak the
     * Activity instance.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

   /* public void updateDisplay() {
        if (userDataList != null) {
            for (UserData userData : userDataList) {
                // output refers to a textview which does not currently exist
                output.append(userData.getName() + "\n");
            }
        }
    } */

  /*  String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    } */

    public void loginUser(String uri, String APIKey, String email, String password) throws IOException {
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("GET");
        requestPackage.setUri(uri);
        requestPackage.setParam("APIKey", APIKey);
        requestPackage.setParam("command", "loginUser");
        requestPackage.setParam("email", email);
        requestPackage.setParam("password", password);

        HttpTask httpTask = new HttpTask();
        httpTask.execute(requestPackage);
    }

    public void registerUser(String uri, String APIKey, String name, String email, String password) throws IOException {
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("POST");
        requestPackage.setUri(uri);
        requestPackage.setParam("APIKey", APIKey);
        requestPackage.setParam("command", "registerUser");
        requestPackage.setParam("email", email);
        requestPackage.setParam("name", name);
        requestPackage.setParam("password", password);

        HttpTask httpTask = new HttpTask();
        httpTask.execute(requestPackage);
    }

    public void resetPassword(String uri, String APIKey, String email, String password) {
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("POST");
        requestPackage.setUri(uri);
        requestPackage.setParam("APIKey", APIKey);
        requestPackage.setParam("command", "resetPassword");
        requestPackage.setParam("email", email);
        requestPackage.setParam("password", password);

        HttpTask task = new HttpTask();
        task.execute(requestPackage);
    }

    public void resetEmail(String uri, String APIKey, String email, String password) {
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("POST");
        requestPackage.setUri(uri);
        requestPackage.setParam("APIKey", APIKey);
        requestPackage.setParam("command", "resetEmail");
        requestPackage.setParam("email", email);
        requestPackage.setParam("password", password);

        HttpTask task = new HttpTask();
        task.execute(requestPackage);
    }

    public static class HttpHandler {
        public static String getDataByMethod(RequestPackage requestPackage) {

            BufferedReader reader = null;
            String uri = requestPackage.getUri();

            if (requestPackage.getMethod().equals("GET")) {
                uri += "?" + requestPackage.getEncodedParams();
            }

            try {
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(requestPackage.getMethod());

                if (requestPackage.getMethod().equals("POST")) {
                    con.setDoOutput(true);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
                    outputStreamWriter.write(requestPackage.getEncodedParams());
                    outputStreamWriter.flush();
                }

                StringBuilder stringBuilder = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.v("Finished", "url connection");
                        return null;
                    }
                }
            }
        }
    }

    private class HttpTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected void onPreExecute() {
            if (mCallbacks != null) {
                mCallbacks.onPreExecute();
            }

            if (apiTask.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            apiTask.add(this);
        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpHandler.getDataByMethod(params[0]);
            return content;

        }

        @Override
        protected void onCancelled() {
            if (mCallbacks != null) {
                mCallbacks.onCancelled();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (mCallbacks != null) {
                mCallbacks.onPostExecute(result);
            }
            // Remove task and set progressBar to invisible
            apiTask.remove(this);
            if (apiTask.size() == 0 ) {
            progressBar.setVisibility(View.INVISIBLE);
            }
            // If no result is returned from the webservice alert user
            if (result == null) {
            Toast.makeText(getActivity(), "Cannot connect to webservice", Toast.LENGTH_LONG).show();
            }
            userDataList = JSONParser.parseUserData(result);


        }
    }

    public static interface OnFragmentInteractionListener {
        void onPreExecute();
        void onCancelled();
        void onPostExecute(String result);
    }
}