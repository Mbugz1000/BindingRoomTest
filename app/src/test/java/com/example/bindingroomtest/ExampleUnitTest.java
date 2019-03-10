package com.example.bindingroomtest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        MyClass myClass = new MyClass();
        int result = myClass.add(2, 2);
        int expected = 4;
        System.out.print(result);
        assertEquals(expected, result);
    }
}