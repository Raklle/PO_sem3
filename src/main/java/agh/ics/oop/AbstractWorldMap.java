package agh.ics.oop;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected MapBoundary mapBoundary = new MapBoundary();

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.position())) {
            mapElements.put(animal.position(), animal);
            animal.addObserver(mapBoundary);
            animal.addObserver(this);
            mapBoundary.addMapElement(animal);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
       return mapElements.get(position);
    }


    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        Bounds bounds = mapBoundary.getBounds();
        return map.draw(bounds.lowerLeft(), bounds.upperRight());
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement obj = mapElements.remove(oldPosition);
        mapElements.put(newPosition, obj);
    }

}


