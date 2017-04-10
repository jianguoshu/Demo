package task.activity.com.classtest;

/**
 * Created on 16/11/30.
 */
public class Child extends Parent{
    public static String childName = "child-field";

    {
        System.out.println("childName is " + childName);
        childName = "child-area";
        System.out.println("childName is " + childName);
    }

    public Child() {
        childName = "child-constructor";
        System.out.println("childName is " + childName);
    }
}
