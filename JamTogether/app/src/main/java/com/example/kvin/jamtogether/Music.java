package com.example.kvin.jamtogether;

/**
 * Created by Asus on 10/12/2015.
 */
public class Music extends IntermediateClass{
    @Override
    public String toString() {
        return "Musique";
    }

    public Music(){
        super();
        content = toString() + id;
    }
}
