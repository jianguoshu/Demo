package task.activity.com.classtest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created on 16/10/17.
 */
public class MyClassTest {
    MyClass myClass;
    @Before
    public void setUp() throws Exception {
        myClass = new MyClass();
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(new ArrayList<String>(), new ArrayList<String>() +  "gaga");
    }
}