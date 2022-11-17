package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap{

    public Vector2d upperBound;
    public static final Vector2d LOWER_BOUND = new Vector2d(0, 0);

    public RectangularMap(int width, int height) {
        upperBound = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(LOWER_BOUND) && position.precedes(upperBound) && !isOccupied(position);
    }

    protected Vector2d[] getCorners(){
        return new Vector2d[]{LOWER_BOUND, upperBound};
    }

    @Override
    public void moved(Vector2d position) {};

}
