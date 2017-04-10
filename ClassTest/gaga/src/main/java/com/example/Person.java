package com.example;

/**
 * Created on 16/10/10.
 */
public class Person {
    public static void say(){
        System.out.println("this is Person");
    }

    public String name = "Person Name";

    public Person() {
        System.out.println("this is Person gouzao");
    }

    static {
        System.out.println("this is Person static daimakuai");
    }

    {
        System.out.println("this is Person daimakuai");
    }
}
