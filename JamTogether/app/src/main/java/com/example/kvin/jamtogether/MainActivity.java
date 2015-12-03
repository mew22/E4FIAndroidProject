package com.example.kvin.jamtogether;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.ui.ParseLoginBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Datastore/Parse Init
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "rSlaIlrY9sxsd2Zi2NU3cw9xZ5y3tf7nXsAm036I", "tyOKMxrci5tHKrOFMvLNgXWLw6mvQwHxRWJ2N0Bx");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        // Ouverture Login
        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
        startActivityForResult(builder.build(), 0);

        setContentView(R.layout.activity_main);
        TextView txt_welc = (TextView) findViewById(R.id.txt_v_welcome);
        txt_welc.setText("Salut, " + ParseUser.getCurrentUser().get("username") + " !");
        txt_welc.setTextColor(Color.WHITE);
        txt_welc.setTextSize(25);

        Button btn_prof = (Button) findViewById(R.id.button_profil);

        btn_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            openProfile(v);}

        });;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void openProfile(View v){

        setContentView(R.layout.profile);

        Button btneditprf = (Button) findViewById(R.id.butEditProfile);
        Button btnretacc = (Button) findViewById(R.id.butReturnWelc);

        TextView tvpseudo = (TextView) findViewById(R.id.tvPseudoEdit);
        TextView tvage = (TextView) findViewById(R.id.tvAgeEdit);
        TextView tvPrenom = (TextView) findViewById(R.id.tvPrenomEdit);
        TextView tvlocation = (TextView) findViewById(R.id.tvLocationEdit);
        TextView tvinstru = (TextView) findViewById(R.id.tvInstrumentPrincEdit);

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

                final Dialog dialog = new Dialog(MainActivity.this);
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


                        //Test de remplissage
                       if (listeditprofile.contains(null)|| listeditprofile.contains("")){

                           error_text.setText("Please fill in all the fields.");
                           error_text.setTextColor(Color.RED);

                       }

                        else {

                           // Actu dans la BDD

                           ParseUser.getCurrentUser().put("username",editpseudo.getText().toString());
                           ParseUser.getCurrentUser().put("age", editage.getText().toString());
                           ParseUser.getCurrentUser().put("name",editprenom.getText().toString());
                           ParseUser.getCurrentUser().put("location",editlocation.getText().toString());
                           ParseUser.getCurrentUser().put("main_instrument",editinst.getText().toString());

                           ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                               public void done(com.parse.ParseException e) {
                                   if (e == null) {
                                       // Save was successful!
                                      Toast.makeText(MainActivity.this,"Profile Updated !", Toast.LENGTH_LONG).show();
                                   } else {
                                       //Failed
                                       Toast.makeText(MainActivity.this,"Error during Update !", Toast.LENGTH_LONG).show();
                                   }
                               }
                           });

                       }

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
