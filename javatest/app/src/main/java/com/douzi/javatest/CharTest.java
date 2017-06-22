package com.douzi.javatest;

/**
 * Created by douzi on 2017/4/25.
 */

public class CharTest {
    public static void main(String[] args) {
        ClassOne one = new ClassOne();

        one.name.replace("12345", "gg");

        System.out.print(one.name);
    }

    static class ClassOne {
        String name = "12345aaaaaa";
    }

}
