package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap{

    public static Vector2d UPPER_BOUND;
    public static final Vector2d LOWER_BOUND = new Vector2d(0, 0);
    private ArrayList<Animal> animalList = new ArrayList<>();

    public RectangularMap(int width, int height) {
        UPPER_BOUND = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(LOWER_BOUND) && position.precedes(UPPER_BOUND) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animalList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal:animalList){
            if(animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);

        return map.draw(LOWER_BOUND, UPPER_BOUND);
    }
}
