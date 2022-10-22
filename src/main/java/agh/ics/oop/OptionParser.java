package agh.ics.oop;

import java.util.Arrays;

public class OptionParser {
    public static MoveDirection[] parse(String[] directions){
        MoveDirection[] directionsEnum = new MoveDirection[directions.length];
        int index = 0;
        for(String direction : directions) {
            switch (direction) {
                case "l", "left" -> directionsEnum[index] = MoveDirection.LEFT;
                case "f", "forward" -> directionsEnum[index] = MoveDirection.FORWARD;
                case "r", "right" -> directionsEnum[index] = MoveDirection.RIGHT;
                case "b", "backwards" -> directionsEnum[index] = MoveDirection.BACKWARD;
                default -> index--;
            }
            index++;
        }
        return Arrays.copyOfRange(directionsEnum, 0, index);
    }
    public static void run(MoveDirection[] directions, Animal animal){
        for(MoveDirection direction : directions) {
            animal.move(direction);
        };

    }

}

