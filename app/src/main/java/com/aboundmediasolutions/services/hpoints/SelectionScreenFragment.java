package com.aboundmediasolutions.services.hpoints;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SelectionScreenFragment extends Fragment {

    private Button mLoginButton, mRegisterButton, mRecoverButton;
    private EditText mEmailEditText, mPasswordEditText;
    private TextView mRegisterTextView, mRecoverTextView;
    private OnFragmentInteractionListener mListener;

    // TAG for testing purposes, REMOVE PRIOR TO RELEASE
    private static String TAG = "SelectionScreenFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Override onCreateView to initialize all of our views
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selection_screen_fragment, container, false);
        // initialize UI components including buttons, pass them to onSelectionFragmentListener
        mLoginButton = (Button) view.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSelectionScreenFragmentInteraction(view);
        }
        });
        // initialize TextViews
        TextView mRegisterTextView = (TextView) view.findViewById(R.id.register_text_view);
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSelectionScreenFragmentInteraction(view);
            }
        });

        TextView mRecoverTextView = (TextView) view.findViewById(R.id.recover_text_view);
        mRecoverTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSelectionScreenFragmentInteraction(view);
            }
        });
        // initialize EditTexts
        mEmailEditText = (EditText)view.findViewById(R.id.email_edit_text);
        mPasswordEditText = (EditText)view.findViewById(R.id.password_edit_text);

        ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);
        return view;
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
        public void onSelectionScreenFragmentInteraction(View view);
    }

}