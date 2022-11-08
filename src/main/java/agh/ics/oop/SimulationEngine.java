package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    @Override
    public void run() {
//        tworzymy i stawiamy na mapie, jesli nie mozna umiescic na mapie to zwwierzatko umiera :(
        ArrayList<Animal> animalList = new ArrayList<>();
        for(Vector2d position:positions){
            Animal newborn = new Animal(map, position);
            if(map.place(newborn)){
                animalList.add(newborn);
            }
        }

        if(animalList.size()==0){
            System.out.println("chujowo");
            return;
        }
        for(int i = 0; i < directions.length; i++){
            animalList.get(i % animalList.size()).move(directions[i]);
        }

    }
}
