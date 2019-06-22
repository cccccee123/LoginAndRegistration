package com.androiddeft.loginandregistration.model;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stcp on 18/6/2019.
 */

public class Car {

    public String  name;
    public String img;
    public String rating;

    public String style;

    public Car(String name, String img,
               String rating,String style) {
        this.name=name;
        this.img=img;
        this.rating=rating;

        this.style=style;
    }

    @Override
    public String toString () {
        return "Cars{" +
                "name='" + name + '\'' +
                ", img='" + img +  '\'' +
                ",rating='" + rating + '\'' +
                ",style'=" + style + '}';
    }




}