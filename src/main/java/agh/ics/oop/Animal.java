package agh.ics.oop;

import java.util.ArrayList;
import java.util.Objects;

public class Animal implements  IMapElement{
    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;
    ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();


    public Animal(IWorldMap map){
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.position = initialPosition;
        this.direction = MapDirection.NORTH;
        this.map = map;
    }

    void addObserver(IPositionChangeObserver observer){
        observerList.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observerList.remove(observer);
    }

    public Vector2d position() {
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


    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : observerList){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    public void move(MoveDirection order){
        final var step = direction.toUnitVector();
        switch (order) {
            case LEFT ->
                direction = direction.previous();
            case RIGHT ->
                direction = direction.next();
            case FORWARD -> {
                if (map.canMoveTo(position.add(step))){
                    positionChanged(position, position.add(step));
                    position = position.add(step);
                }
            }
            case BACKWARD -> {
                if (map.canMoveTo(position.subtract(step))) {
                    positionChanged(position, position.subtract(step));
                    position = position.subtract(step);
                }
            }
        }
    }
}
//    f b r l f f r r f f f f f f f f