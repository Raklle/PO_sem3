package agh.ics.oop;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private final TreeSet<Vector2d> xSet = new TreeSet<>((Vector2d v1, Vector2d v2) ->{
        if(v2.x() == v1.x()){
            return v1.y() - v2.y();
        }else{
            return v1.x() - v2.x();
        }
    });
    private final TreeSet<Vector2d> ySet = new TreeSet<>((Vector2d v1, Vector2d v2) ->{
        if(v2.y() == v1.y()){
            return v1.x() - v2.x();
        }else{
            return v1.y() - v2.y();
        }
    });

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
    public int getHeight(){
        return xSet.last().y() - xSet.first().y();
    }
    public int getWidth(){
        return xSet.last().x() - xSet.first().x();
    }
}
