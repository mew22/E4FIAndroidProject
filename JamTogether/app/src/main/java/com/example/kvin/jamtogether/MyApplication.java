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

        public String session;
        public boolean login;
        public boolean TwoPane;
        public MenuItem item;

        public List<Group> list_group = new ArrayList<Group>();
        public List<Instrument> list_instrument = new ArrayList<Instrument>();
        public List<Music> list_music= new ArrayList<Music>();
        public List<Profil> list_profil = new ArrayList<Profil>(); // for search
        public List<Session> list_session= new ArrayList<Session>();
        public List<Invitation> list_invitation =  new ArrayList<Invitation>();

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
