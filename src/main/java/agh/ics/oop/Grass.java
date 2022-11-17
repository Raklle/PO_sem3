package agh.ics.oop;

public record Grass(Vector2d position) {

    @Override
    public String toString() {
        return "*";
    }
}
