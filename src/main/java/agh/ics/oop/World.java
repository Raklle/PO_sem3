package agh.ics.oop;



public class World {


    public static void main(String[] args) {

        System.out.println("System startuje");
        Animal something = new Animal();
        System.out.println(something);
        OptionParser.run(OptionParser.parse(args),something);
        System.out.println(something);
        System.out.println("System zakonczyl dzialanie");
//
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));




    }

}

