package com.example.kvin.jamtogether.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kvin.jamtogether.R;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.ui.ParseLoginBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProfilFragment extends Fragment {
    Button btneditprf;
    Button btnretacc;
    TextView tvpseudo;
    TextView tvage;
    TextView tvPrenom;
    TextView tvlocation;
    TextView tvinstru;

    //private OnFragmentInteractionListener mListener;


    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        openProfile(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        btneditprf = (Button) v.findViewById(R.id.butEditProfile);
        btnretacc = (Button) v.findViewById(R.id.butReturnWelc);

        tvpseudo = (TextView) v.findViewById(R.id.tvPseudoEdit);
        tvage = (TextView) v.findViewById(R.id.tvAgeEdit);
        tvPrenom = (TextView) v.findViewById(R.id.tvPrenomEdit);
        tvlocation = (TextView) v.findViewById(R.id.tvLocationEdit);
        tvinstru = (TextView) v.findViewById(R.id.tvInstrumentPrincEdit);
        return v;
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

    public void openProfile(View v){


        // Histoire de check si on l'a déjà fait ou pas ( la vérif full field plus bas empêche de se marcher dessus)
        if(ParseUser.getCurrentUser().get("age") != null) {
            tvpseudo.setText(ParseUser.getCurrentUser().get("username").toString());
            tvage.setText(ParseUser.getCurrentUser().get("age").toString());
            tvPrenom.setText(ParseUser.getCurrentUser().get("name").toString());
            tvlocation.setText(ParseUser.getCurrentUser().get("location").toString());
            tvinstru.setText(ParseUser.getCurrentUser().get("main_instrument").toString());
        }


        btneditprf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.edit_profile_dialog);
                dialog.setCancelable(true);

                final EditText editpseudo = (EditText) dialog.findViewById(R.id.editpseudo);
                final EditText editprenom = (EditText) dialog.findViewById(R.id.edit_prenom);
                final EditText editage = (EditText) dialog.findViewById(R.id.edit_age);
                final EditText editlocation = (EditText) dialog.findViewById(R.id.edit_location);
                final EditText editinst = (EditText) dialog.findViewById(R.id.edit_main_inst);

                final TextView error_text = (TextView) dialog.findViewById(R.id.texterroredit);

                Button confirm = (Button) dialog.findViewById(R.id.button_confirm_edition);
                Button cancel = (Button) dialog.findViewById(R.id.button_cancel_edition);

                final List<EditText> listeditprofile = new ArrayList<EditText>();



                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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

                        ParseUser.getCurrentUser().

                                put("username",editpseudo.getText()

                                                .

                                                        toString()

                                );
                        ParseUser.getCurrentUser().

                                put("age",editage.getText()

                                                .

                                                        toString()

                                );
                        ParseUser.getCurrentUser().

                                put("name",editprenom.getText()

                                                .

                                                        toString()

                                );
                        ParseUser.getCurrentUser().

                                put("location",editlocation.getText()

                                                .

                                                        toString()

                                );
                        ParseUser.getCurrentUser().

                                put("main_instrument",editinst.getText()

                                                .

                                                        toString()

                                );

                        ParseUser.getCurrentUser().

                                saveInBackground(new SaveCallback() {
                                                     public void done (com.parse.ParseException e){
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


                        //Réactualisation du Profil
                        dialog.dismiss();

                        openProfile(v);
                    }

                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        openProfile(v);

                    }

                });
                dialog.show();
            }

        });



    }

}
