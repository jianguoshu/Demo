package com.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MyClass {

    public static void main(String[] args) {

        List<String> haha = new LinkedList<>();

        haha.add("bv");
        haha.add("sd");
        haha.add("sv");
        haha.add("sv");
        haha.add("rg");
        haha.add("erg");
        haha.add("avb");
        haha.add("sg");

        Collections.sort(haha);

        System.out.println(haha);

        ListIterator<String> iterator = haha.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("sg")) {
                iterator.remove();
                iterator.add("gagaGA");
                iterator.add("gagaGA");
                iterator.add("gagaGA");
                iterator.add("gagaGA");
            }
            System.out.println(next);
        }

//        for (String str : haha) {
//            System.out.println(str);
//            if (str.equals("sg")) {
//                str = "heh";
//            }
//        }
        System.out.println(haha);
    }

    public List<String> add(List<String> list, String element) {
        list.add(element);
        return list;
    }
}




