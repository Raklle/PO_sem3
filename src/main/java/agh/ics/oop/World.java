package agh.ics.oop;

import java.util.Objects;

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
    public static Direction[] stringToEnum(String[] directions){
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
    public static class Vector2d {
        private final int x;
        private final int y;
        public Vector2d(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "(" + x + "," + y + ")";
        }
        public boolean precedes(Vector2d other){
            return other.x >= x && other.y >= y;
        }
        public boolean follows(Vector2d other){
            return other.x <= x && other.y <= y;
        }
        public Vector2d add(Vector2d other){
            return new Vector2d(x + other.x, y + other.y);
        }
        public Vector2d subtract(Vector2d other){
            return new Vector2d(x - other.x, y - other.y);
        }
        public Vector2d upperRight(Vector2d other){
            return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
        }
        public Vector2d lowerLeft(Vector2d other){
            return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
        }
        public Vector2d opposite(){
            return new Vector2d(-x, -y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vector2d vector2d = (Vector2d) o;
            return x == vector2d.x && y == vector2d.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public static void main(String[] args) {

        System.out.println("System startuje");
        run(stringToEnum(args));
        System.out.println("System zakonczyl dzialanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection test = MapDirection.NORTH;
        System.out.println(test.next());
        System.out.println(test.previous());
        System.out.println(test.toString());
        System.out.println(test.toUnitVector());
    }

}

