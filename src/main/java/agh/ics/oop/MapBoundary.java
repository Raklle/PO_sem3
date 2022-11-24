package agh.ics.oop;
import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    public TreeSet<Vector2d> xSet = new TreeSet<>(Comparator.comparingInt(Vector2d::x));
    public TreeSet<Vector2d> ySet = new TreeSet<>(Comparator.comparingInt(Vector2d::y));

    public void addMapElement(IMapElement newElement){
        Vector2d position = newElement.position();
        xSet.add(position);
        ySet.add(position);
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xSet.remove(oldPosition);
        ySet.remove(oldPosition);
        xSet.add(newPosition);
        ySet.add(newPosition);
    }

    public Bounds getBounds(){
        Vector2d upperBound = new Vector2d(xSet.last().x(), ySet.last().y());
        Vector2d lowerBound = new Vector2d(xSet.first().x(), ySet.first().y());
        return new Bounds(lowerBound, upperBound);
    }

}
