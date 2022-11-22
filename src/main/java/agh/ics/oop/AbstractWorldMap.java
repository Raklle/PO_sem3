package agh.ics.oop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private final ArrayList<Animal> animalList = new ArrayList<>();

    protected ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    protected Map<Vector2d, IMapElement> mapElements = new HashMap<>();

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.position())) {
            mapElements.put(animal.position(), animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
       return mapElements.get(position);
    }

    protected abstract Bounds getCorners();

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        Bounds bounds = getCorners();
        return map.draw(bounds.lowerLeft(), bounds.upperRight());
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement obj = mapElements.remove(oldPosition);
        mapElements.put(newPosition, obj);
    }

}


