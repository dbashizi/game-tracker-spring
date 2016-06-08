package com.tiy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localdom on 5/25/2016.
 */
public class CodingBatExercisesTest {

    CodingBatExercises exercises = new CodingBatExercises();

    @Before
    public void setUp() {
    }

    @Test
    public void testEvenlySpaced() throws Exception {
        assertEquals(false, exercises.evenlySpaced(20, 30, 42));
    }

    @Test
    public void testEvenlySpaced2() throws Exception {
        assertEquals(true, exercises.evenlySpaced(30, 30, 30));
    }

    @Test
    public void testEvenlySpaced3() throws Exception {
        assertEquals(false, exercises.evenlySpaced(10, 30, 100));
    }

    @Test
    public void testEvenlySpaced4() throws Exception {
        assertEquals(false, exercises.evenlySpaced(80, 30, 30));
    }

    @Test
    public void testEvenlySpaced5() throws Exception {
        assertEquals(true, exercises.evenlySpaced(40, 60, 20));
    }

    @Test
    public void testEvenlySpaced6() throws Exception {
        assertEquals(true, exercises.evenlySpaced(40, 30, 20));
    }

    @Test
    public void testEvenlySpaced7() throws Exception {
        assertEquals(false, exercises.evenlySpaced(20, 40, 30));
    }

    @Test
    public void testEvenlySpaced8() throws Exception {
        assertEquals(false, exercises.evenlySpaced(40, 20, 30));
    }

    @Test
    public void testEvenlySpaced9() throws Exception {
        assertEquals(false, exercises.evenlySpaced(40, 30, 40));
    }

    @Test
    public void testEvenlySpaced10() throws Exception {
        assertEquals(true, exercises.evenlySpaced(2, 4, 0));
    }

    @Test
    public void testWithEnoughSmalls() throws Exception {
        assertEquals(1, exercises.makeChocolate(1, 1, 6).smalls);
        assertEquals(1, exercises.makeChocolate(1, 1, 6).bigs);
        assertTrue(exercises.makeChocolate(1, 1, 6).hasSolution);
        assertEquals(0, exercises.makeChocolate(5, 5, 25).smalls);
        assertEquals(5, exercises.makeChocolate(5, 5, 25).bigs);
        assertEquals(1, exercises.makeChocolate(5, 5, 26).smalls);
        assertEquals(5, exercises.makeChocolate(5, 5, 26).bigs);
        assertEquals(2, exercises.makeChocolate(5, 5, 27).smalls);
        assertEquals(5, exercises.makeChocolate(5, 5, 27).bigs);
        assertEquals(3, exercises.makeChocolate(5, 5, 28).smalls);
        assertEquals(5, exercises.makeChocolate(5, 5, 28).bigs);
        assertEquals(4, exercises.makeChocolate(5, 5, 29).smalls);
        assertEquals(5, exercises.makeChocolate(5, 5, 29).bigs);
    }

    @Test
    public void testNotEnough() throws Exception {
        assertEquals(-1, exercises.makeChocolate(1, 1, 7).smalls);
        assertFalse(exercises.makeChocolate(1, 1, 7).hasSolution);
        assertEquals(-1, exercises.makeChocolate(1, 1, 7).bigs);
        assertEquals(-1, exercises.makeChocolate(5, 5, 31).smalls);
        assertEquals(-1, exercises.makeChocolate(5, 5, 31).bigs);
    }

    @Test
    public void testNotEnoughSmalls() throws Exception {
        assertEquals(-1, exercises.makeChocolate(1, 2, 8).smalls);
        assertEquals(-1, exercises.makeChocolate(1, 2, 8).bigs);
    }

    @Test
    public void testOnlyWithSmalls() throws Exception {
        assertEquals(6, exercises.makeChocolate(10, 0, 6).smalls);
        assertEquals(0, exercises.makeChocolate(10, 0, 6).bigs);
    }

    @Test
    public void testOnlyWithBigs() throws Exception {
        assertEquals(0, exercises.makeChocolate(0, 10, 20).smalls);
    }

    @Test
    public void testNotEnoughBigs() throws Exception {
        assertEquals(-1, exercises.makeChocolate(4, 1, 10).smalls);
    }

    @Test
    public void testMakeChocolate7() throws Exception {
        assertEquals(1, exercises.makeChocolate(1, 1, 6).smalls);
    }

    @Test
    public void testMakeChocolate8() throws Exception {
        assertEquals(1, exercises.makeChocolate(1, 1, 6).smalls);
    }

    @Test
    public void testMakeChocolate9() throws Exception {
        assertEquals(1, exercises.makeChocolate(1, 1, 6).smalls);
    }

    @Test
    public void testMakeChocolate10() throws Exception {
        assertEquals(1, exercises.makeChocolate(1, 1, 6).smalls);
    }

    @Test
    public void testMakeChocolateNotEnoughBigs() throws Exception {
        assertEquals(6, exercises.makeChocolate(7, 2, 16).smalls);
    }

    @Test
    public void testMakeChocolateUseLotsOfSmalls() throws Exception {
        assertEquals(6, exercises.makeChocolate(12, 7, 41).smalls);
    }

    @Test
    public void plusOutSimpleTest() throws Exception {
        String str = "100ejkkj1oiuakw9283";
        String word = "kj";

        String expected = "++++++kj+++++++++++";
        assertEquals(expected, exercises.plusOut(str, word));
    }

    @Test
    public void testPlusOutTwoReplacements() throws Exception {
        String str = "100ejkkj1oiuakj";
        String word = "kj";

        String expected = "++++++kj+++++kj";
        assertEquals(expected, exercises.plusOut(str, word));
    }

    @Test
    public void testPlusOutAllRelaced() throws Exception {
        String str = "kjkjkjkjkjkjkjkjkjkjkj";
        String word = "kj";

        String expected = str;
        assertEquals(expected, exercises.plusOut(str, word));
    }

    @Test
    public void testPlusOutNoReplacement() throws Exception {
        String str = "something without the word in it";
        String word = "kj";

        String expected = "++++++++++++++++++++++++++++++++";
        assertEquals(expected, exercises.plusOut(str, word));
    }

    @Test
    public void testPlusOutStartWithReplacement() throws Exception {
        String str = "21onetwothree21fourfivesix21";
        String word = "21";

        String expected = "21+++++++++++21+++++++++++21";
        assertEquals(expected, exercises.plusOut(str, word));
    }

    @Test
    public void testPlusOutBasicExamples() throws Exception {
        assertEquals("++xy++", exercises.plusOut("12xy34", "xy"));
        assertEquals("1+++++", exercises.plusOut("12xy34", "1"));
        assertEquals("++xy++xy+++xy", exercises.plusOut("12xy34xyabcxy", "xy"));
    }
}