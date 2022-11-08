package agh.ics.oop;

public class SingleAnimalSimulation {
    public static void runOneAnimal(MoveDirection[] directions, Animal animal){
        for(MoveDirection direction : directions) {
            animal.move(direction);
        }

    }
}
