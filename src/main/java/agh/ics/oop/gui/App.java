package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    private IWorldMap map;
    private final GridPane gridPane = new GridPane();
    private static final int CELL_SIZE = 30;

    @Override
    public void start(Stage primaryStage) throws Exception {


        drawFrame();
        gridPane.setGridLinesVisible(true);
        drawMap();

        Scene scene = new Scene(gridPane, gridPane.getPrefWidth(), gridPane.getPrefHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        System.out.println(map);
        this.map = new GrassField(20);
        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions, true);
        engine.run();
    }



    private Vector2d getGridPosition(Vector2d mapPosition){
        Bounds bounds = map.getBounds();
        int x = mapPosition.x() - bounds.lowerLeft().x() +1 ;
        int y = bounds.upperRight().y() + 1 - mapPosition.y();
       return new Vector2d(x, y);
    }

    private void drawFrame(){
        Bounds bounds = map.getBounds();
        int width = bounds.upperRight().x() -  bounds.lowerLeft().x() + 1;
        int height = bounds.upperRight().y() -  bounds.lowerLeft().y() + 1;
        for(int i = 1; i <= width; i++){
            Label label = new Label(bounds.lowerLeft().x()+ i - 1 + "");
            gridPane.add(label, i, 0);
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        for(int j = height; j >= 1; j--){
            Label label =new Label(bounds.upperRight().y() - j +1  + "");
            gridPane.add(label, 0, j);
            GridPane.setHalignment(label, HPos.CENTER);

            gridPane.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
        Label label0 = new Label("y\\x");
        gridPane.add(label0, 0, 0);
        GridPane.setHalignment(label0, HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        gridPane.getRowConstraints().add(new RowConstraints(CELL_SIZE));
    }

    private void drawObject(Vector2d position){
        Object object = map.objectAt(position);
        if(object == null){
            return;
        }
        Vector2d gridPosition = getGridPosition(position);
        Label label = new Label(object.toString());
        gridPane.add(label, gridPosition.x(), gridPosition.y());
        GridPane.setHalignment(label, HPos.CENTER);
    }

    private void drawMap(){
        Bounds bounds = map.getBounds();

        for(int x = bounds.lowerLeft().x(); x <= bounds.upperRight().x(); x++){
            for(int y = bounds.lowerLeft().y(); y <= bounds.upperRight().y(); y++){
                drawObject(new Vector2d(x, y));
            }
        }
    }
}
