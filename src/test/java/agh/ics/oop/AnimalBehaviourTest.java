package agh.ics.oop;

import org.junit.jupiter.api.Test;


import static agh.ics.oop.OptionsParser.parse;
import static agh.ics.oop.SingleAnimalSimulation.*;
import static org.junit.jupiter.api.Assertions.*;

class AnimalBehaviourTest {
    private final RectangularMap catMap = new RectangularMap(4, 4);
    private final RectangularMap dogMap = new RectangularMap(4, 4);
    private final Vector2d start = new Vector2d(2, 2);

    @Test
    void animalCanWalkAndTurn() {
        //given
        Animal cat = new Animal(catMap, start);
        String[] ordersCat = {"f", "right", "forward", "f", "l", "left"};
        Animal dog = new Animal(dogMap, start);
        String[] ordersDog = {"r", "r", "r", "b", "backwards", "right"};

        //when
        runOneAnimal(parse(ordersCat), cat);
        runOneAnimal(parse(ordersDog), dog);

        //then
        assertTrue(cat.isAt(new Vector2d(4, 3)));
        assertTrue(cat.isFacing(MapDirection.WEST));
        assertTrue(dog.isAt(new Vector2d(4, 2)));
        assertTrue(dog.isFacing(MapDirection.NORTH));

    }
    @Test
    void unknownCommandsThrowException() {
        //given
        Animal cat = new Animal(catMap, start);
        String[] ordersCat = {"f", "zxcvzx", "zxcv", "jump", "bonkward", "f"};
        Animal dog = new Animal(dogMap, start);
        String[] ordersDog = {"r", "jgffgx", "gfv", "sit", "backflip", "right"};

        //then
        assertThrows(IllegalArgumentException.class, () -> runOneAnimal(parse(ordersCat), cat));

        assertThrows(IllegalArgumentException.class, () -> runOneAnimal(parse(ordersDog), dog));

    }

    @Test
    void animalCantLeaveMap() {
        //given
        Animal cat = new Animal(catMap, start);
        String[] ordersCat = {"f", "f", "f", "f", "f"};
        Animal dog = new Animal(dogMap, start);
        String[] ordersDog = {"f", "f", "f", "r","f", "f", "f"};


        //when
        runOneAnimal(parse(ordersCat), cat);
        runOneAnimal(parse(ordersDog), dog);

        //then
        assertTrue(cat.isAt(new Vector2d(2, 4)));
        assertTrue(cat.isFacing(MapDirection.NORTH));
        assertTrue(dog.isAt(new Vector2d(4, 4)));
        assertTrue(dog.isFacing(MapDirection.EAST));

    }

}