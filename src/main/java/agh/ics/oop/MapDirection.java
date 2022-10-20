package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        return switch (this) {
            case EAST -> "Wschod";
            case WEST -> "Zachod";
            case SOUTH -> "Poludnie";
            case NORTH -> "Polnoc";
        };
    }
    public MapDirection next(){
        return switch (this) {
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case SOUTH -> WEST;
            case NORTH -> EAST;
        };
    }
    public MapDirection previous(){
        return switch (this) {
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case NORTH -> WEST;
        };
    }
    public World.Vector2d toUnitVector(){
        return switch (this) {
            case EAST -> new World.Vector2d(1, 0);
            case WEST -> new World.Vector2d(-1, 0);
            case SOUTH -> new World.Vector2d(0, -1);
            case NORTH -> new World.Vector2d(0, 1);
        };
    }

}
