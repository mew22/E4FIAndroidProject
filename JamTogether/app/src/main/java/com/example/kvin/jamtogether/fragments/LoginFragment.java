package com.example.kvin.jamtogether.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.kvin.jamtogether.MyApplication;
import com.example.kvin.jamtogether.R;
import com.example.kvin.jamtogether.interfaces.onLogIn;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class LoginFragment extends Fragment {


    private onLogIn onlgin = new onLogIn() {
        @Override
        public void lgin() {

        }
    };

    @Override
    public void onStart() {
        super.onStart();

        ParseLoginBuilder builder = new ParseLoginBuilder(getActivity());
        startActivityForResult(builder.build(), 0);
    }

    public void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == getActivity().RESULT_OK) {
                ((MyApplication)getActivity().getApplication()).login = true;
                onlgin.lgin();
            }
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (!(activity instanceof onLogIn)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        onlgin = (onLogIn) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    public LoginFragment() {
        // Required empty public constructor
    }

}
