package com.example.kvin.jamtogether;

/**
 * Created by Asus on 10/12/2015.
 */
public class Instrument extends IntermediateClass{
    @Override
    public String toString() {
        return "Instrument";
    }

    public Instrument(){
        super();
        content = toString() + id;
    }
}
