package com.douzi.clonetest;

/**
 * Created on 2017/2/4.
 */

public class MainClass {
    public static void main(String[] args) {
        CustomClone object1 = new CustomClone();

        CustomClone object2 = (CustomClone) object1.clone();

        System.out.print("object1 : " + object1);
        System.out.print("object2 : " + object2);
    }
}
