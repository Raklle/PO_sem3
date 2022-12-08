package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable, IMapUpdateObserver{
    private MoveDirection[] directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private final boolean drawSteps;
    private final ArrayList<IMapUpdateObserver> observerList = new ArrayList<>();
    private ArrayList<Animal> animalList;
    private int moveDelay;


    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, int moveDelay) {
        this(directions, map, positions, moveDelay, false);
    }
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions,int moveDelay, boolean printSteps) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        this.drawSteps = printSteps;
        this.moveDelay = moveDelay;
        placeAnimals();
    }

    private void placeAnimals(){
        animalList = new ArrayList<>();
        for(Vector2d position:positions){
            Animal newborn = new Animal(map, position);
            map.place(newborn);
            animalList.add(newborn);
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < directions.length; i++){
            animalList.get(i % animalList.size()).move(directions[i]);
            positionChanged();
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                System.out.println("cokolwiek");
            }
            if(drawSteps) System.out.println(map);
        }
    }


    public void addObserver(IMapUpdateObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(IMapUpdateObserver observer){
        observerList.remove(observer);
    }

    @Override
    public void positionChanged() {
        for(IMapUpdateObserver observer : observerList){
            observer.positionChanged();
        }
    }

    public void setDirections(MoveDirection[] directions){
        this.directions = directions;
    }
}

