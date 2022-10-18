package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public static String toString(MapDirection direction) {
        return switch (direction) {
            case EAST -> "Wschod";
            case WEST -> "Zachod";
            case SOUTH -> "Poludnie";
            case NORTH -> "Polnoc";
        };
    }
    public static MapDirection next(MapDirection direction){
        return switch (direction) {
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case SOUTH -> WEST;
            case NORTH -> EAST;
        };
    }
    public static MapDirection previous(MapDirection direction){
        return switch (direction) {
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case NORTH -> WEST;
        };
    }
    public static World.Vector2d toUnitVector(MapDirection direction){
        return switch (direction) {
            case EAST -> new World.Vector2d(1, 0);
            case WEST -> new World.Vector2d(-1, 0);
            case SOUTH -> new World.Vector2d(0, -1);
            case NORTH -> new World.Vector2d(0, 1);
        };
    }

}
