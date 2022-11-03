package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.OptionParser.*;
import static org.junit.jupiter.api.Assertions.*;

class AnimalBehaviourTest {

    @Test
    void animalCanWalkAndTurn() {
        //given
        Animal cat = new Animal();
        String[] ordersCat = {"f", "right", "forward", "f", "l", "left"};
        Animal dog = new Animal();
        String[] ordersDog = {"r", "r", "r", "b", "backwards", "right"};

        //when
        run(parse(ordersCat), cat);
        run(parse(ordersDog), dog);

        //then
        assertTrue(cat.isAt(new Vector2d(4, 3)));
        assertTrue(cat.isFacing(MapDirection.WEST));
        assertTrue(dog.isAt(new Vector2d(4, 2)));
        assertTrue(dog.isFacing(MapDirection.NORTH));

    }
    @Test
    void animalIgnoresUnknownCommands() {
        //given
        Animal cat = new Animal();
        String[] ordersCat = {"f", "zxcvzx", "zxcv", "jump", "bonkward", "f"};
        Animal dog = new Animal();
        String[] ordersDog = {"r", "jgffgx", "gfv", "sit", "backflip", "right"};

        //when
        run(parse(ordersCat), cat);
        run(parse(ordersDog), dog);

        //then
        assertTrue(cat.isAt(new Vector2d(2, 4)));
        assertTrue(cat.isFacing(MapDirection.NORTH));
        assertTrue(dog.isAt(new Vector2d(2, 2)));
        assertTrue(dog.isFacing(MapDirection.SOUTH));

    }

    @Test
    void animalCantLeaveMap() {
        //given
        Animal cat = new Animal();
        String[] ordersCat = {"f", "zxcvzx", "f", "f", "f", "f"};
        Animal dog = new Animal();
        // 9x forward -> right -> 8x forward -> right -> 12x forward
        String[] ordersDog = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "r","f", "f", "f", "f","f", "f", "f", "f", "r","f", "f", "f", "f","f", "f", "f", "f","f", "f", "f", "f"};


        //when
        run(parse(ordersCat), cat);
        run(parse(ordersDog), dog);

        //then
        assertTrue(cat.isAt(new Vector2d(2, 4)));
        assertTrue(cat.isFacing(MapDirection.NORTH));
        assertTrue(dog.isAt(new Vector2d(4, 0)));
        assertTrue(dog.isFacing(MapDirection.SOUTH));

    }

}