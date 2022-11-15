package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleAnimalMovementTest {

    @Test
    void testFromGithub() {
    //given
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

    //when
        MoveDirection[] directions = new OptionsParser().parse(args);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

    //then
        assertTrue(map.isOccupied(new Vector2d(3, 5)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
//        System.out.println(map);
    }

    @Test
    void animalsCantOverlap() {
        //given
        String[] args = {"f"};
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2) };

        //when
        MoveDirection[] directions = new OptionsParser().parse(args);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        //then
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
//        System.out.println(map);
    }
    @Test
    void animalsCantPassOthers() {
        //given
        String[] args = {"r","l","f","f","f","f","f"};
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,2) };

        //when
        MoveDirection[] directions = new OptionsParser().parse(args);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        //then
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(3, 2)));
//        System.out.println(map);
    }
}