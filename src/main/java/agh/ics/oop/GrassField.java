package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

public class GrassField extends AbstractWorldMap{

    private final ArrayList<Grass> grassList = new ArrayList<>();
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
                grassList.add(new Grass(new Vector2d(x, y)));
            }
        }
    };

    public void addGrass(Vector2d position){
        grassList.add(new Grass(position));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if(object != null){
            return object;
        }
        for(Grass grass: grassList){
            if(grass.position().equals(position)){
                return grass;
            }
        }
        return null;
    }

    protected Vector2d[] getCorners(){

        Vector2d lower = new Vector2d(100000, 100000);
        Vector2d upper = new Vector2d(-100000, -100000);
        for(Grass grass: grassList){
            lower = lower.lowerLeft(grass.position());
            upper = upper.upperRight(grass.position());
        }
        for(Animal animal:animalList){
            lower = lower.lowerLeft(animal.getPosition());
            upper = upper.upperRight(animal.getPosition());
        }
        return new Vector2d[]{lower, upper};
    }
    private Grass grassAt(Vector2d position) {
        for (Grass grass : grassList) {
            if (grass.position().equals(position)) {
                return grass;
            }
        }
        return null;
    }
    @Override
    public void moved(Vector2d position) {
        if(grassAt(position) != null){
            generateGrass(1);
            grassList.remove(this.grassAt(position));

        }
    }


}


