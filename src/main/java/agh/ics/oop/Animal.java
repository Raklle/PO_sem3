package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal(){
        this.position = new Vector2d(2, 2);
        this.direction = MapDirection.NORTH;
    }

    @Override
    public String toString() {
        return "Zwierze " +
                "znajduje sie w punkcie " + position +
                " i patrzy na " + direction.toString();
    }

    boolean isAt(Vector2d target){
        return position.equals(target);
    }
    boolean isFacing(MapDirection target){ return direction.equals(target); }

    public void move(MoveDirection order){
        switch (order) {
            case LEFT ->
                direction = direction.previous();
            case RIGHT ->
                direction = direction.next();
            case FORWARD ->
                position = position.add(direction.toUnitVector());
            case BACKWARD ->
                position = position.subtract(direction.toUnitVector());

        }
        position = position.lowerLeft(new Vector2d(4, 4));
        position = position.upperRight(new Vector2d(0,0));
    }
}
