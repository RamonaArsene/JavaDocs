package com.teamnet.examples;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ramona.arsene on 7/10/2017.
 */
public class MyUnitTest {
    @Test
    public void testConcatenate(){
        MyClass unit = new MyClass();
        String result = unit.Concatenate("one", "two");
        assertEquals("onetwo", result);

    }

    @Test
    public void testConcatenateNull(){
        MyClass unit = new MyClass();
        String result = unit.Concatenate("one", null);
        assertEquals("one", result);
    }

}
