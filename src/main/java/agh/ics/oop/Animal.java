package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;

    public Animal(IWorldMap map){
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.position = initialPosition;
        this.direction = MapDirection.NORTH;
        this.map = map;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return switch (this.direction) {
            case EAST -> ">";
            case WEST -> "<";
            case SOUTH -> "v";
            case NORTH -> "^";
        };
    }

    public boolean isAt(Vector2d target){
        return Objects.equals(this.position, target);
    }

    public boolean isFacing(MapDirection target){ return direction.equals(target); }

    public void move(MoveDirection order){
        final var step = direction.toUnitVector();
        switch (order) {
            case LEFT ->
                direction = direction.previous();
            case RIGHT ->
                direction = direction.next();
            case FORWARD -> {
                if (map.canMoveTo(position.add(step))){
                    position = position.add(step);
                }
            }
            case BACKWARD -> {
                if (map.canMoveTo(position.subtract(step))) {
                    position = position.subtract(step);
                }
            }
        }
        map.moved(position);
    }
}
