package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    IWorldMap map = new GrassField(10);
    @Override
    public void start(Stage primaryStage) throws Exception {


        Label label1 = new Label("0,0");
        Label label2 = new Label("1.5");
        Label label3 = new Label("Label 2");
        Label label4 = new Label("Label 3");
        Label label5 = new Label("1, 4");
        Label label6 = new Label("2, 5");
        Label label7 = new Label("1, 6");

        GridPane gridPane = new GridPane();
        gridPane.add(label1, 0, 0);
        gridPane.add(label2, 1, 1);
        gridPane.add(label3, 2, 2);
        gridPane.add(label4, 0, 3);
        gridPane.add(label5, 1, 4);
        gridPane.add(label6, 2, 5);
        gridPane.add(label7, 1, 6);

        gridPane.setGridLinesVisible(true);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {

        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions, true);
        engine.run();
    }
}
