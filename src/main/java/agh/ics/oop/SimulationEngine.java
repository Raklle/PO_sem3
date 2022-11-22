package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private final boolean drawSteps;


    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this(directions, map, positions, false);
    }
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, boolean printSteps) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        this.drawSteps = printSteps;
    }

    @Override
    public void run() {
        ArrayList<Animal> animalList = new ArrayList<>();
        for(Vector2d position:positions){
            Animal newborn = new Animal(map, position);
            if(map.place(newborn)){
                animalList.add(newborn);
            }
        }
        System.out.println(map);
        for(int i = 0; i < directions.length; i++){
            animalList.get(i % animalList.size()).move(directions[i]);
            if(drawSteps) System.out.println(map);
        }
    }

}

