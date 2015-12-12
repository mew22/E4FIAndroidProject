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
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);


        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
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
                final EditText editpseudo = (EditText) root.findViewById(R.id.editpseudo);
                final EditText editprenom = (EditText) root.findViewById(R.id.edit_prenom);
                final EditText editage = (EditText) root.findViewById(R.id.edit_age);
                final EditText editlocation = (EditText) root.findViewById(R.id.edit_location);
                final EditText editinst = (EditText) root.findViewById(R.id.edit_main_inst);

                final TextView error_text = (TextView) root.findViewById(R.id.texterroredit);

                final List<EditText> listeditprofile = new ArrayList<EditText>();



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
                                listeditprofile.add(editpseudo);
                                listeditprofile.add(editage);
                                listeditprofile.add(editprenom);
                                listeditprofile.add(editlocation);
                                listeditprofile.add(editinst);
                                boolean full = false;

                                //Test de remplissage

                                while (full == false) {

                                    for (EditText elt : listeditprofile) {

                                        if (TextUtils.isEmpty(elt.getText().toString())) {
                                            elt.setError("Please fill this item.");
                                            error_text.setText("Please verify that all fields are written.");
                                            error_text.setTextColor(Color.RED);
                                            return;
                                        } else {
                                            full = true;
                                        }
                                    }

                                }
                                // Actu dans la BDD
                                ParseUser.getCurrentUser().put("username", editpseudo.getText().toString());
                                ParseUser.getCurrentUser().put("age", editage.getText().toString());
                                ParseUser.getCurrentUser().put("name", editprenom.getText().toString());
                                ParseUser.getCurrentUser().put("location", editlocation.getText().toString());
                                ParseUser.getCurrentUser().put("main_instrument", editinst.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                                                                public void done(com.parse.ParseException e) {
                                                                                    if (e == null) {
                                                                                        // Save was successful!

                                                                                        Toast.makeText(getActivity(), "Profile Updated !", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        //Failed
                                                                                        Toast.makeText(getActivity(), "Error during Update !", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }
                                                                            }
                                );
                            }
                        });
                builder.create().show();
                break;
            }
            case R.id.nav_music:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_music_item_dialog, null);
                final EditText editpseudo = (EditText) root.findViewById(R.id.editpseudo);
                final EditText editprenom = (EditText) root.findViewById(R.id.edit_prenom);
                final EditText editage = (EditText) root.findViewById(R.id.edit_age);
                final EditText editlocation = (EditText) root.findViewById(R.id.edit_location);
                final EditText editinst = (EditText) root.findViewById(R.id.edit_main_inst);

                final TextView error_text = (TextView) root.findViewById(R.id.texterroredit);

                final List<EditText> listeditprofile = new ArrayList<EditText>();



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
                                listeditprofile.add(editpseudo);
                                listeditprofile.add(editage);
                                listeditprofile.add(editprenom);
                                listeditprofile.add(editlocation);
                                listeditprofile.add(editinst);
                                boolean full = false;

                                //Test de remplissage

                                while (full == false) {

                                    for (EditText elt : listeditprofile) {

                                        if (TextUtils.isEmpty(elt.getText().toString())) {
                                            elt.setError("Please fill this item.");
                                            error_text.setText("Please verify that all fields are written.");
                                            error_text.setTextColor(Color.RED);
                                            return;
                                        } else {
                                            full = true;
                                        }
                                    }

                                }
                                // Actu dans la BDD
                                ParseUser.getCurrentUser().put("username", editpseudo.getText().toString());
                                ParseUser.getCurrentUser().put("age", editage.getText().toString());
                                ParseUser.getCurrentUser().put("name", editprenom.getText().toString());
                                ParseUser.getCurrentUser().put("location", editlocation.getText().toString());
                                ParseUser.getCurrentUser().put("main_instrument", editinst.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                                                                public void done(com.parse.ParseException e) {
                                                                                    if (e == null) {
                                                                                        // Save was successful!

                                                                                        Toast.makeText(getActivity(), "Profile Updated !", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        //Failed
                                                                                        Toast.makeText(getActivity(), "Error during Update !", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }
                                                                            }
                                );
                            }
                        });
                builder.create().show();
                break;
            }
            case R.id.nav_instruments:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_instrument_item_dialog, null);
                final EditText editpseudo = (EditText) root.findViewById(R.id.editpseudo);
                final EditText editprenom = (EditText) root.findViewById(R.id.edit_prenom);
                final EditText editage = (EditText) root.findViewById(R.id.edit_age);
                final EditText editlocation = (EditText) root.findViewById(R.id.edit_location);
                final EditText editinst = (EditText) root.findViewById(R.id.edit_main_inst);

                final TextView error_text = (TextView) root.findViewById(R.id.texterroredit);

                final List<EditText> listeditprofile = new ArrayList<EditText>();



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit "+ mItem.content)
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
                                listeditprofile.add(editpseudo);
                                listeditprofile.add(editage);
                                listeditprofile.add(editprenom);
                                listeditprofile.add(editlocation);
                                listeditprofile.add(editinst);
                                boolean full = false;

                                //Test de remplissage

                                while (full == false) {

                                    for (EditText elt : listeditprofile) {

                                        if (TextUtils.isEmpty(elt.getText().toString())) {
                                            elt.setError("Please fill this item.");
                                            error_text.setText("Please verify that all fields are written.");
                                            error_text.setTextColor(Color.RED);
                                            return;
                                        } else {
                                            full = true;
                                        }
                                    }

                                }
                                // Actu dans la BDD
                                ParseUser.getCurrentUser().put("username", editpseudo.getText().toString());
                                ParseUser.getCurrentUser().put("age", editage.getText().toString());
                                ParseUser.getCurrentUser().put("name", editprenom.getText().toString());
                                ParseUser.getCurrentUser().put("location", editlocation.getText().toString());
                                ParseUser.getCurrentUser().put("main_instrument", editinst.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                                                                public void done(com.parse.ParseException e) {
                                                                                    if (e == null) {
                                                                                        // Save was successful!

                                                                                        Toast.makeText(getActivity(), "Profile Updated !", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        //Failed
                                                                                        Toast.makeText(getActivity(), "Error during Update !", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }
                                                                            }
                                );
                            }
                        });
                builder.create().show();
                break;
            }
            case R.id.nav_sessions:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_session_item_dialog, null);
                final EditText editpseudo = (EditText) root.findViewById(R.id.editpseudo);
                final EditText editprenom = (EditText) root.findViewById(R.id.edit_prenom);
                final EditText editage = (EditText) root.findViewById(R.id.edit_age);
                final EditText editlocation = (EditText) root.findViewById(R.id.edit_location);
                final EditText editinst = (EditText) root.findViewById(R.id.edit_main_inst);

                final TextView error_text = (TextView) root.findViewById(R.id.texterroredit);

                final List<EditText> listeditprofile = new ArrayList<EditText>();



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
                                listeditprofile.add(editpseudo);
                                listeditprofile.add(editage);
                                listeditprofile.add(editprenom);
                                listeditprofile.add(editlocation);
                                listeditprofile.add(editinst);
                                boolean full = false;

                                //Test de remplissage

                                while (full == false) {

                                    for (EditText elt : listeditprofile) {

                                        if (TextUtils.isEmpty(elt.getText().toString())) {
                                            elt.setError("Please fill this item.");
                                            error_text.setText("Please verify that all fields are written.");
                                            error_text.setTextColor(Color.RED);
                                            return;
                                        } else {
                                            full = true;
                                        }
                                    }

                                }
                                // Actu dans la BDD
                                ParseUser.getCurrentUser().put("username", editpseudo.getText().toString());
                                ParseUser.getCurrentUser().put("age", editage.getText().toString());
                                ParseUser.getCurrentUser().put("name", editprenom.getText().toString());
                                ParseUser.getCurrentUser().put("location", editlocation.getText().toString());
                                ParseUser.getCurrentUser().put("main_instrument", editinst.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                                                                public void done(com.parse.ParseException e) {
                                                                                    if (e == null) {
                                                                                        // Save was successful!

                                                                                        Toast.makeText(getActivity(), "Profile Updated !", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        //Failed
                                                                                        Toast.makeText(getActivity(), "Error during Update !", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }
                                                                            }
                                );
                            }
                        });
                builder.create().show();
                break;
            }
            case R.id.nav_invitations:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_invitation_item_dialog, null);
                final EditText editpseudo = (EditText) root.findViewById(R.id.editpseudo);
                final EditText editprenom = (EditText) root.findViewById(R.id.edit_prenom);
                final EditText editage = (EditText) root.findViewById(R.id.edit_age);
                final EditText editlocation = (EditText) root.findViewById(R.id.edit_location);
                final EditText editinst = (EditText) root.findViewById(R.id.edit_main_inst);

                final TextView error_text = (TextView) root.findViewById(R.id.texterroredit);

                final List<EditText> listeditprofile = new ArrayList<EditText>();



                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit "+ mItem.content)
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
                                listeditprofile.add(editpseudo);
                                listeditprofile.add(editage);
                                listeditprofile.add(editprenom);
                                listeditprofile.add(editlocation);
                                listeditprofile.add(editinst);
                                boolean full = false;

                                //Test de remplissage

                                while (full == false) {

                                    for (EditText elt : listeditprofile) {

                                        if (TextUtils.isEmpty(elt.getText().toString())) {
                                            elt.setError("Please fill this item.");
                                            error_text.setText("Please verify that all fields are written.");
                                            error_text.setTextColor(Color.RED);
                                            return;
                                        } else {
                                            full = true;
                                        }
                                    }

                                }
                                // Actu dans la BDD
                                ParseUser.getCurrentUser().put("username", editpseudo.getText().toString());
                                ParseUser.getCurrentUser().put("age", editage.getText().toString());
                                ParseUser.getCurrentUser().put("name", editprenom.getText().toString());
                                ParseUser.getCurrentUser().put("location", editlocation.getText().toString());
                                ParseUser.getCurrentUser().put("main_instrument", editinst.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                                                                public void done(com.parse.ParseException e) {
                                                                                    if (e == null) {
                                                                                        // Save was successful!

                                                                                        Toast.makeText(getActivity(), "Profile Updated !", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        //Failed
                                                                                        Toast.makeText(getActivity(), "Error during Update !", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }
                                                                            }
                                );
                            }
                        });
                builder.create().show();
                break;
            }
            default:{
                root = getActivity().getLayoutInflater().inflate(R.layout.edit_instrument_item_dialog, null);
                final EditText editpseudo = (EditText) root.findViewById(R.id.editpseudo);
                final EditText editprenom = (EditText) root.findViewById(R.id.edit_prenom);
                final EditText editage = (EditText) root.findViewById(R.id.edit_age);
                final EditText editlocation = (EditText) root.findViewById(R.id.edit_location);
                final EditText editinst = (EditText) root.findViewById(R.id.edit_main_inst);

                final TextView error_text = (TextView) root.findViewById(R.id.texterroredit);

                final List<EditText> listeditprofile = new ArrayList<EditText>();



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
                                listeditprofile.add(editpseudo);
                                listeditprofile.add(editage);
                                listeditprofile.add(editprenom);
                                listeditprofile.add(editlocation);
                                listeditprofile.add(editinst);
                                boolean full = false;

                                //Test de remplissage

                                while (full == false) {

                                    for (EditText elt : listeditprofile) {

                                        if (TextUtils.isEmpty(elt.getText().toString())) {
                                            elt.setError("Please fill this item.");
                                            error_text.setText("Please verify that all fields are written.");
                                            error_text.setTextColor(Color.RED);
                                            return;
                                        } else {
                                            full = true;
                                        }
                                    }

                                }
                                // Actu dans la BDD
                                ParseUser.getCurrentUser().put("username", editpseudo.getText().toString());
                                ParseUser.getCurrentUser().put("age", editage.getText().toString());
                                ParseUser.getCurrentUser().put("name", editprenom.getText().toString());
                                ParseUser.getCurrentUser().put("location", editlocation.getText().toString());
                                ParseUser.getCurrentUser().put("main_instrument", editinst.getText().toString());
                                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                                                                public void done(com.parse.ParseException e) {
                                                                                    if (e == null) {
                                                                                        // Save was successful!

                                                                                        Toast.makeText(getActivity(), "Profile Updated !", Toast.LENGTH_LONG).show();
                                                                                    } else {
                                                                                        //Failed
                                                                                        Toast.makeText(getActivity(), "Error during Update !", Toast.LENGTH_LONG).show();
                                                                                    }
                                                                                }
                                                                            }
                                );
                            }
                        });
                builder.create().show();
                break;
            }
        }
    }
}

