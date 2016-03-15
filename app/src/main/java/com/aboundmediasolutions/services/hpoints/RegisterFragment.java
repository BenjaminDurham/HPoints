package com.aboundmediasolutions.services.hpoints;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class RegisterFragment extends Fragment {

    private Button mClearButton, mRegisterButton;
    private EditText mEmailEditText, mNameEditText, mPasswordEditText, mVerifyPasswordEditText;
    private OnFragmentInteractionListener mListener;

    // TAG for testing purposes, REMOVE PRIOR TO RELEASE
    private static String TAG = "RegisterFragment";

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Override onCreateView to initialize all of our views
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        // initialize UI components including buttons, pass them to onSelectionFragmentListener
        mClearButton = (Button) view.findViewById(R.id.register_clear_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRegisterFragmentInteraction(view);
            }
        });
        mRegisterButton = (Button) view.findViewById(R.id.register_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRegisterFragmentInteraction(view);
            }
        });
        // initialize EditTexts
        mEmailEditText = (EditText)view.findViewById(R.id.register_email_edit_text);
        mNameEditText = (EditText)view.findViewById(R.id.register_name_edit_text);
        mPasswordEditText = (EditText)view.findViewById(R.id.register_password_edit_text);
        mVerifyPasswordEditText = (EditText)view.findViewById(R.id.register_password_verify_edit_text);

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
        public void onRegisterFragmentInteraction(View view);
    }
}