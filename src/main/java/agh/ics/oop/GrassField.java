package agh.ics.oop;

import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

public class GrassField extends AbstractWorldMap{

    private final int grassSpawn;

    public GrassField(int n) {
        grassSpawn = (int) Math.sqrt(n*10);
        generateGrass(n);
    }

    private void generateGrass(int n){
        for(int i = 0; i< n; i++){
            int x = ThreadLocalRandom.current().nextInt(0, grassSpawn);
            int y = ThreadLocalRandom.current().nextInt(0, grassSpawn);
            if(isOccupied(new Vector2d(x, y))){
                 i--;
            }else {
                Grass grass = new Grass(new Vector2d(x,y));
                mapBoundary.addMapElement(grass);
                mapElements.put(grass.position(), grass);
            }
        }
    }


    public void addGrass(Vector2d position){
        Grass grass = new Grass(position);
        mapElements.put(grass.position(), grass);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }


    private Grass grassAt(Vector2d position) {
        if( objectAt(position) instanceof Grass){
            return (Grass) objectAt(position);
        }
        return null;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeGrass(newPosition);
        super.positionChanged(oldPosition, newPosition);
    }

    @Override
    public void place(Animal animal) {
        removeGrass(animal.position());
        super.place(animal);
    }

    private void removeGrass(Vector2d position){
        if(grassAt(position) != null ){
            generateGrass(1);
            mapElements.remove(position);
        }
    }
}


