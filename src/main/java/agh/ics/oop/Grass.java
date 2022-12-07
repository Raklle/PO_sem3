package agh.ics.oop;

public record Grass(Vector2d position) implements IMapElement {

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getImageSrc() {
        return "src/main/resources/super_zajebista_recznie_robiona_trawa.png";
    }
}
