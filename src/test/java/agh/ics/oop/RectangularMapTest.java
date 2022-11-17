package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void objectAtTest() {
        //given
        Vector2d emptyPosition = new Vector2d(2, 2);

        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(3, 3);
        Animal animal = new Animal(map, animalPosition);

        //when
        map.place(animal);
        //then
        assertNull(map.objectAt(emptyPosition));
        assertTrue(map.objectAt(animalPosition) instanceof Animal);

    }

    @Test
    void canMoveToTest() {
        //given
        Vector2d emptyPosition = new Vector2d(2, 2);

        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(3, 3);
        Animal animal = new Animal(map, animalPosition);

        //when
        map.place(animal);

        //then
        assertFalse(map.canMoveTo(animalPosition));
        assertTrue(map.canMoveTo(emptyPosition));
    }

    @Test
    void isOccupiedTest() {
        //given
        Vector2d emptyPosition = new Vector2d(2, 2);

        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(3, 3);
        Animal animal = new Animal(map, animalPosition);

        //when
        map.place(animal);

        //then
        assertTrue(map.isOccupied(animalPosition));
        assertFalse(map.isOccupied(emptyPosition));
    }
}