package com.example.kvin.jamtogether;

/**
 * Created by Asus on 10/12/2015.
 */
public class Session extends IntermediateClass{
    @Override
    public String toString() {
        return "Session";
    }

    public Session(){
        super();
        content = toString() + id;
    }
}
