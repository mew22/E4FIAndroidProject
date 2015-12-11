package com.example.kvin.jamtogether;

import android.app.Application;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 10/12/2015.
 */
public class MyApplication extends Application{


        public List<User> list_user = new ArrayList<User>(); // for search
        public User current_user = new User();
        public boolean login;
        public boolean TwoPane;
        public MenuItem item;

        public MyApplication() {

        }

        @Override
        public void onCreate() {
                super.onCreate();

                // Datastore/Parse Init
                Parse.enableLocalDatastore(this);
                Parse.initialize(this, "rSlaIlrY9sxsd2Zi2NU3cw9xZ5y3tf7nXsAm036I", "tyOKMxrci5tHKrOFMvLNgXWLw6mvQwHxRWJ2N0Bx");
                ParseInstallation.getCurrentInstallation().saveInBackground();
        }
}
