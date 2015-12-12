package com.example.kvin.jamtogether.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kvin.jamtogether.MyApplication;
import com.example.kvin.jamtogether.R;
import com.example.kvin.jamtogether.activity.MainActivity;
import com.example.kvin.jamtogether.interfaces.Callbacks;
import com.example.kvin.jamtogether.interfaces.onLogOut;
import com.parse.ParseUser;


/**
 * A simple {@link DialogFragment} subclass.
 */
public class LogoutFragment extends DialogFragment {


    private onLogOut onlgout = new onLogOut() {
        @Override
        public void lgout() {

        }
    };

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (!(activity instanceof onLogOut)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        onlgout = (onLogOut) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Are you sure you want to log out ?")
                .setTitle("Exiting your JamTogether Session")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParseUser.logOut();
                        ((MyApplication)getActivity().getApplication()).login = false;
                        onlgout.lgout();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            return;
                    }
                });


builder.create().show();
        return null;
    }

}