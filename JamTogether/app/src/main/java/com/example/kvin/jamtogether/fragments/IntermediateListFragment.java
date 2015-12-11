package com.example.kvin.jamtogether.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kvin.jamtogether.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class IntermediateListFragment extends Fragment {


    public IntermediateListFragment() {
        // Required empty public constructor
    }

    public abstract void onItemSelected(String id);

}
