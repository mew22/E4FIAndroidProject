package com.example.kvin.jamtogether;

/**
 * Created by Asus on 12/12/2015.
 */
public abstract class IntermediateClass {

    private static int auto_increment = 0;
    public int id = 0;

    public String content = "pouet";
    public String details = "lolzer";

    IntermediateClass(){
        id = auto_increment++;
    }
}
