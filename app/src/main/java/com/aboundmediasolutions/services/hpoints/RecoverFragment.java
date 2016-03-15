package com.aboundmediasolutions.services.hpoints;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class RecoverFragment extends Fragment {

    private Button mClearButton, mRegisterButton;
    private OnFragmentInteractionListener mListener;

    // TAG for testing purposes, REMOVE PRIOR TO RELEASE
    private static String TAG = "RecoverFragment";

    // Override onCreateView to initialize all of our views
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.recover_fragment, container, false);

        ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Communicator");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onRecoverFragmentInteraction(View view);
    }

}