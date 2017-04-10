package com.example;

/**
 * Created on 16/10/10.
 */
public class Student extends Person {
    public static void say(){
        System.out.println("this is Student");
    }
    public String name = "Student Name";

    public Student() {
        System.out.println("this is Student gouzao");
    }

    static {
        System.out.println("this is Student static daimakuai");
    }

    {
        System.out.println("this is Student daimakuai");
    }
}
