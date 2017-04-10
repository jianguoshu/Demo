package com.douzi.clonetest;

/**
 * Created on 2017/2/4.
 */

public class CustomClone implements Cloneable{
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
