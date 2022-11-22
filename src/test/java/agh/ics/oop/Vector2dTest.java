package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);

        //when
        String s1 = v1.toString();
        String s2 = v2.toString();

        //then
        assertEquals(s1, "(1,2)");
        assertEquals(s2, "(-1,0)");
    }

    @Test
    void precedes() {

        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);
        Vector2d v3 = new Vector2d(3, 3);


        assertFalse(v1.precedes(v2));
        assertTrue(v1.precedes(v3));
        assertFalse(v3.precedes(v2));
    }

    @Test
    void follows() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);
        Vector2d v3 = new Vector2d(3, 3);


        assertTrue(v1.follows(v2));
        assertFalse(v1.follows(v3));
        assertTrue(v3.follows(v2));
    }

    @Test
    void add() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);

        //when
        Vector2d add1 = v1.add(v2);
        Vector2d add2 = v2.add(v2);

        //then
        assertEquals(add1, new Vector2d(0, 2));
        assertEquals(add2, new Vector2d(-2, 0));
    }

    @Test
    void subtract() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);

        //when
        Vector2d sub1 = v1.subtract(v2);
        Vector2d sub2 = v2.subtract(v2);

        //then
        assertEquals(sub1, new Vector2d(2, 2));
        assertEquals(sub2, new Vector2d(0, 0));
    }

    @Test
    void upperRight() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);
        Vector2d v3 = new Vector2d(3, -3);

        //when
        Vector2d test1 = v1.upperRight(v2);
        Vector2d test2 = v2.upperRight(v3);

        //then
        assertEquals(test1, new Vector2d(1, 2));
        assertEquals(test2, new Vector2d(3, 0));
    }

    @Test
    void lowerLeft() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);
        Vector2d v3 = new Vector2d(3, -3);

        //when
        Vector2d test1 = v1.lowerLeft(v2);
        Vector2d test2 = v2.lowerLeft(v3);

        //then
        assertEquals(test1, new Vector2d(-1, 0));
        assertEquals(test2, new Vector2d(-1, -3));
    }

    @Test
    void opposite() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-1, 0);
        Vector2d v3 = new Vector2d(3, -3);

        //when
        Vector2d test1 = v1.opposite();
        Vector2d test2 = v2.opposite();
        Vector2d test3 = v3.opposite();

        //then
        assertEquals(test1, new Vector2d(-1, -2));
        assertEquals(test2, new Vector2d(1, 0));
        assertEquals(test3, new Vector2d(-3, 3));
    }

    @Test
    void testEquals() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(3, -3);

        //then
        assertEquals(v1, v2);
        assertNotEquals(v3, v2);

    }
}