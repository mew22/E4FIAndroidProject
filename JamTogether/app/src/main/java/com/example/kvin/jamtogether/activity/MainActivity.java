package com.example.kvin.jamtogether.activity;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.kvin.jamtogether.MyApplication;
import com.example.kvin.jamtogether.R;
import com.example.kvin.jamtogether.fragments.GroupsFragment;
import com.example.kvin.jamtogether.fragments.IntermediateListFragment;
import com.example.kvin.jamtogether.fragments.ItemDetailFragment;
import com.example.kvin.jamtogether.fragments.ItemListFragment;
import com.example.kvin.jamtogether.fragments.ProfilFragment;
import com.example.kvin.jamtogether.fragments.SearchFragment;
import com.example.kvin.jamtogether.interfaces.Callbacks;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Callbacks {

    private TextView txt_welc;
    private Button btn_prof;
    private boolean login;
    private DrawerLayout drawer;
    private FrameLayout content_main;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if not already login

        if(!((MyApplication)getApplication()).login) {
            setContentView(R.layout.activity_main_logout);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            content_main = (FrameLayout) drawer.findViewById(R.id.content_main);
        }
        else{
            setContentView(R.layout.activity_main_login);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            View v = navigationView.getHeaderView(0);

            txt_welc = (TextView) v.findViewById(R.id.tv_hello);
            txt_welc.setText("Salut, " + ParseUser.getCurrentUser().get("username") + " !");

            content_main = (FrameLayout)drawer.findViewById(R.id.content_main);
            navigationView.getMenu().getItem(1).setChecked(true);
        }

        /*txt_welc = (TextView) findViewById(R.id.txt_v_welcome);
        btn_prof = (Button) findViewById(R.id.button_profil);

        btn_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            openProfile(v);}

        });;*/
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(((MyApplication)getApplication()).item != null){
            onNavigationItemSelected(((MyApplication)getApplication()).item);
        }

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Handle Parse login return activity
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                /*txt_welc.setText("Salut, " + ParseUser.getCurrentUser().get("username") + " !");
                txt_welc.setTextColor(Color.WHITE);
                txt_welc.setTextSize(25);*/

                ((MyApplication)getApplication()).login = true;
                setContentView(R.layout.activity_main_login);
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();

                navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);

                View v = navigationView.getHeaderView(0);

                txt_welc = (TextView) v.findViewById(R.id.tv_hello);
                txt_welc.setText("Salut, " + ParseUser.getCurrentUser().get("username") + " !");

                content_main = (FrameLayout)drawer.findViewById(R.id.content_main);
                navigationView.getMenu().getItem(1).setChecked(true);


                //TODO: Generate each list in MyApplication except list<Profil>
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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




/*    public void openProfile(View v){

        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.profile, content_main);

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
                                Toast.makeText(MainActivity.this, "Profile Updated !", Toast.LENGTH_LONG).show();
                            } else {
                                //Failed
                                Toast.makeText(MainActivity.this, "Error during Update !", Toast.LENGTH_LONG).show();
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



    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        fragment = null;
        Class fragmentClass;
        boolean parse_de_merde = false;

        switch(item.getItemId()){ //for each menu item in nav

            case R.id.nav_search:{
                fragmentClass = SearchFragment.class;
                break;
            }
            case R.id.nav_login : {
                // Ouverture Login
                parse_de_merde = true;
                fragmentClass = ProfilFragment.class;
                ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
                startActivityForResult(builder.build(), 0);
                break;
            }
            case R.id.nav_profil: {
                fragmentClass = ProfilFragment.class;
                //openProfile(null);
                break;
            }
            case R.id.nav_groups:{
                fragmentClass = GroupsFragment.class;
                break;
            }
            case R.id.nav_music:{
                fragmentClass = ProfilFragment.class;
                break;
            }
            case R.id.nav_instruments:{
                fragmentClass = ProfilFragment.class;
                break;
            }
            case R.id.nav_logout: {
                fragmentClass = ProfilFragment.class;
                break;
            }
            case R.id.nav_sessions:{
                fragmentClass = ProfilFragment.class;
                break;
            }
            case R.id.nav_invitations:{
                fragmentClass = ProfilFragment.class;
                break;
            }
            default:{
                fragmentClass = ProfilFragment.class;
                break;
            }
        }

        // If not for login, we use fragments
        if(!parse_de_merde) {
            try {
                ((MyApplication)getApplication()).item = item;
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

        }
        // Highlight the selected item, update the title, and close the drawer
        item.setChecked(true);
        setTitle(item.getTitle());

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Handle fragments type of FragmentList
    @Override
    public void onItemSelected(String id) {

        //((GroupsFragment)fragment).onItemSelected(id);
        /*if (((MyApplication)getApplication()).TwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }*/
        ((IntermediateListFragment)fragment).onItemSelected(id);
    }
}

