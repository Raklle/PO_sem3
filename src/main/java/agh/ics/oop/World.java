package agh.ics.oop;

public class World {
    public static void run(Direction[] directions){
        System.out.println("zwierzak rusza do przodu");
        for(Direction direction : directions) {
            switch (direction) {
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
            };

        }
        }
    public static Direction[] StringToEnum(String[] directions){
        Direction[] directionsEnum = new Direction[directions.length];
        int i =0;
        for(String direction : directions) {
            directionsEnum[i] = switch (direction) {
                case "l" ->  Direction.LEFT;
                case "f" -> Direction.FORWARD;
                case "r" -> Direction.RIGHT;
                case "b" -> Direction.BACKWARD;
                default ->  Direction.IGNORE;
            }
            ;
            i++;
        }
        return directionsEnum;
    }
    public static void main(String[] args) {
        System.out.println("System startuje");
        run(StringToEnum(args));
        System.out.println("System zakonczyl dzialanie");
    }

}

