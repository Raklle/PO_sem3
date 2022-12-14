package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] directions){
        MoveDirection[] directionsEnum = new MoveDirection[directions.length];
        int index = 0;
        for(String direction : directions) {
            switch (direction) {
                case "l", "left" -> directionsEnum[index] = MoveDirection.LEFT;
                case "f", "forward" -> directionsEnum[index] = MoveDirection.FORWARD;
                case "r", "right" -> directionsEnum[index] = MoveDirection.RIGHT;
                case "b", "backwards" -> directionsEnum[index] = MoveDirection.BACKWARD;
                default -> throw (new IllegalArgumentException(direction + " is not legal move specification"));
            }
            index++;
        }
        return Arrays.copyOfRange(directionsEnum, 0, index);
    }
}

