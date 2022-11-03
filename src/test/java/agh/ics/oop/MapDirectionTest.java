package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapDirectionTest {

    @Test
    void testToString() {

    }

    @Test
    void nextTest() {
        assertEquals(MapDirection.EAST.next() ,MapDirection.SOUTH);
        assertEquals(MapDirection.SOUTH.next() ,MapDirection.WEST);
        assertEquals(MapDirection.NORTH.next() ,MapDirection.EAST);
        assertEquals(MapDirection.WEST.next() ,MapDirection.NORTH);
    }

    @Test
    void previousTest() {
        assertEquals(MapDirection.EAST.previous() ,MapDirection.NORTH);
        assertEquals(MapDirection.SOUTH.previous() ,MapDirection.EAST);
        assertEquals(MapDirection.NORTH.previous() ,MapDirection.WEST);
        assertEquals(MapDirection.WEST.previous() ,MapDirection.SOUTH);
    }

}