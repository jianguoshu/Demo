package task.activity.com.classtest;

/**
 * Created on 16/11/30.
 */
public class Parent {
    public static String parentName = "parent-field";

    {
        System.out.println("parentName is " + parentName);
        parentName = "parent-area";
        System.out.println("parentName is " + parentName);
    }

    public Parent() {
        parentName = "parent-constructor";
        System.out.println("parentName is " + parentName);
    }
}
