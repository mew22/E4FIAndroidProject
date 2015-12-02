package com.example.kvin.jamtogether;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

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
        txt_welc.setText( "Salut  !" +ParseUser.getCurrentUser().get("username"));
        txt_welc.setTextColor(Color.WHITE);
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

       // Button btneditprf = (Button) findViewById(R.id.btneditprofile);
       // Button btnretacc = (Button) findViewById(R.id.btnretacc);








    }
}
