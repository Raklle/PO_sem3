package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void objectAtTest() {

        //given
        GrassField map = new GrassField(0);

        Vector2d animalPosition = new Vector2d(2, 2);
        Vector2d grassPosition = new Vector2d(4, 4);
        Vector2d emptyPosition = new Vector2d(6, 6);

        Animal animal = new Animal(map, animalPosition);
        Animal animalOnGrass = new Animal(map, grassPosition);

        //when
        map.addGrass(grassPosition);
        map.place(animal);

        //then
        assertNull(map.objectAt(emptyPosition));
        assertTrue(map.objectAt(grassPosition)  instanceof Grass);
        assertTrue(map.objectAt(animalPosition) instanceof Animal);

        //Returns animal before grass
        map.place(animalOnGrass);
        assertTrue(map.objectAt(grassPosition) instanceof Animal);
    }

    @Test
    void canMoveToTest() {
        //given
        GrassField map = new GrassField(0);

        Vector2d animalPosition = new Vector2d(2, 2);
        Vector2d grassPosition = new Vector2d(4, 4);
        Vector2d emptyPosition = new Vector2d(6, 6);

        Animal animal = new Animal(map, animalPosition);

        //when
        map.addGrass(grassPosition);
        map.place(animal);

        //then
        assertFalse(map.canMoveTo(animalPosition));
        assertTrue(map.canMoveTo(grassPosition));
        assertTrue(map.canMoveTo(emptyPosition));
    }

    @Test
    void isOccupiedTest() {
        //given
        GrassField map = new GrassField(0);

        Vector2d animalPosition = new Vector2d(2, 2);
        Vector2d grassPosition = new Vector2d(4, 4);
        Vector2d emptyPosition = new Vector2d(6, 6);

        Animal animal = new Animal(map, animalPosition);

        //when
        map.addGrass(grassPosition);
        map.place(animal);

        //then
        assertTrue(map.isOccupied(animalPosition));
        assertTrue(map.isOccupied(grassPosition));
        assertFalse(map.isOccupied(emptyPosition));
    }
    @Test
    void movedTest() {
        //given
        GrassField map = new GrassField(1);

        Vector2d grassPosition = new Vector2d(2, 3);
        //when
        map.addGrass(grassPosition);
//        System.out.println(map);

        //then
        assertTrue(map.objectAt(grassPosition) instanceof Grass);
        map.moved(grassPosition);
        assertNull(map.objectAt(grassPosition));
//        System.out.println(map);
    }

}