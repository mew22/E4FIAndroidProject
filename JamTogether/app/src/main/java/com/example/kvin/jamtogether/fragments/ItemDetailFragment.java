package com.example.kvin.jamtogether.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kvin.jamtogether.Group;
import com.example.kvin.jamtogether.Instrument;
import com.example.kvin.jamtogether.IntermediateClass;
import com.example.kvin.jamtogether.Invitation;
import com.example.kvin.jamtogether.Music;
import com.example.kvin.jamtogether.MyApplication;
import com.example.kvin.jamtogether.R;
import com.example.kvin.jamtogether.Session;
import com.example.kvin.jamtogether.User;
import com.example.kvin.jamtogether.fragments.dummy.DummyContent;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private IntermediateClass mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(((MyApplication)getActivity().getApplication()).item.getItemId() == R.id.nav_search){
            inflater.inflate(R.menu.search_menu, menu);
        }else if(((MyApplication)getActivity().getApplication()).item.getItemId() == R.id.nav_invitations){
            inflater.inflate(R.menu.invite_menu, menu);
        }else{
            inflater.inflate(R.menu.edit_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.edit:{
                openEditDialog();
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            switch(((MyApplication)getActivity().getApplication()).item.getItemId()){

                case R.id.nav_search:{
                    //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

                    break;
                }
                case R.id.nav_groups:{
                    for(IntermediateClass i : ((MyApplication)getActivity().getApplication()).current_user.list_group){
                        String tmp = i.id + "";
                        if(tmp.equals(getArguments().getString(ARG_ITEM_ID))){
                            mItem = i;
                            break;
                        }
                    }
                    break;
                }
                case R.id.nav_music:{
                    for(IntermediateClass i : ((MyApplication)getActivity().getApplication()).current_user.list_music){
                        String tmp = i.id + "";
                        if(tmp.equals(getArguments().getString(ARG_ITEM_ID))){
                            mItem = i;
                            break;
                        }
                    }
                    break;
                }
                case R.id.nav_instruments:{
                    for(IntermediateClass i : ((MyApplication)getActivity().getApplication()).current_user.list_instrument){
                        String tmp = i.id + "";
                        if(tmp.equals(getArguments().getString(ARG_ITEM_ID))){
                            mItem = i;
                            break;
                        }
                    }
                    break;
                }
                case R.id.nav_sessions:{
                    for(IntermediateClass i : ((MyApplication)getActivity().getApplication()).current_user.list_session){
                        String tmp = i.id + "";
                        if(tmp.equals(getArguments().getString(ARG_ITEM_ID))){
                            mItem = i;
                            break;
                        }
                    }
                    break;
                }
                case R.id.nav_invitations:{
                    for(IntermediateClass i : ((MyApplication)getActivity().getApplication()).current_user.list_invitation){
                        String tmp = i.id + "";
                        if(tmp.equals(getArguments().getString(ARG_ITEM_ID))){
                            mItem = i;
                            break;
                        }
                    }
                    break;
                }
                default:{
                    for(IntermediateClass i : ((MyApplication)getActivity().getApplication()).current_user.list_instrument){
                        String tmp = i.id + "";
                        if(tmp.equals(getArguments().getString(ARG_ITEM_ID))){
                            mItem = i;
                            break;
                        }
                    }
                    break;
                }
            }


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = null;

        switch(((MyApplication)getActivity().getApplication()).item.getItemId()) { //for each menu item in nav

            case R.id.nav_search: {
                break;
            }
            case R.id.nav_groups: {
                rootView = inflater.inflate(R.layout.detail_group_item, container, false);
                break;
            }
            case R.id.nav_music:{
                rootView = inflater.inflate(R.layout.detail_music_item, container, false);
                break;
            }
            case R.id.nav_instruments:{
                rootView = inflater.inflate(R.layout.detail_instrument_item, container, false);
                break;
            }
            case R.id.nav_sessions:{
                rootView = inflater.inflate(R.layout.detail_session_item, container, false);
                break;
            }
            case R.id.nav_invitations:{
                rootView = inflater.inflate(R.layout.detail_invitation_item, container, false);
                break;
            }
            default:{
                rootView = inflater.inflate(R.layout.detail_instrument_item, container, false);
                break;
            }
        }

        return rootView;
    }

    public void openEditDialog() {
        View root;
        switch(((MyApplication)getActivity().getApplication()).item.getItemId()){ //for each menu item in nav

            case R.id.nav_search:{
                break;
            }
            case R.id.nav_groups:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_group_item_dialog, null);



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit " + mItem.content)
                        .setView(root)
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
                break;
            }
            case R.id.nav_music:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_music_item_dialog, null);



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit " + mItem.content)
                        .setView(root)
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
                break;
            }
            case R.id.nav_instruments:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_instrument_item_dialog, null);



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit " + mItem.content)
                        .setView(root)
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
                break;
            }
            case R.id.nav_sessions:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_session_item_dialog, null);



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit " + mItem.content)
                        .setView(root)
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
                break;            }
            case R.id.nav_invitations:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_invitation_item_dialog, null);



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit " + mItem.content)
                        .setView(root)
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
                break;
            }
            default:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_instrument_item_dialog, null);



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit " + mItem.content)
                        .setView(root)
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create().show();
                break;
            }
        }
    }
}

