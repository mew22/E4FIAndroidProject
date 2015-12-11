package com.example.kvin.jamtogether;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 10/12/2015.
 */
public class User {

    public List<Group> list_group = new ArrayList<Group>();
    public List<Instrument> list_instrument = new ArrayList<Instrument>();
    public List<Music> list_music= new ArrayList<Music>();
    public List<Session> list_session= new ArrayList<Session>();
    public List<Invitation> list_invitation =  new ArrayList<Invitation>();
    public List<String> list_role=  new ArrayList<String>();            // TODO: list of enum ?

    public String session;
    public String username;


    @Override
    public String toString() {
        return "";
    }
}
