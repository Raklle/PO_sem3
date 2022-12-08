package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class App extends Application implements IMapUpdateObserver{
    private IWorldMap map;
    private final GridPane gridPane = new GridPane();
    public static final int CELL_SIZE = 60;
    private final GuiElementBox elementBox = new GuiElementBox(false);
    private SimulationEngine engine;

    @Override
    public void start(Stage primaryStage) throws Exception {


        Button start = new Button("Start");
        Button example = new Button("Run example");
        TextField moves = new TextField();
        String ex = "f b r l f f r r f f f f f f f f b b b b";
        Label exampleDirections = new Label(" " + ex);
        VBox controls = new VBox(moves, start,new Label("--------------------"), example, exampleDirections);
        HBox main = new HBox(this.gridPane, controls);

        start.setOnAction(click -> {
            this.engine.setDirections(OptionsParser.parse(moves.getText().split(" ")));
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });
        example.setOnAction(click -> {
            this.engine.setDirections(OptionsParser.parse(ex.split(" ")));
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });
        updateMap();
        Scene scene = new Scene(main, 1000,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        System.out.println(map);
        this.map = new GrassField(3);
        MoveDirection[] directions = new MoveDirection[0];
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        engine = new SimulationEngine(directions, map, positions, 300);
        engine.addObserver(this);
        Thread thread = new Thread(engine);
        thread.start();
    }



    private Vector2d getGridPosition(Vector2d mapPosition){
        Bounds bounds = map.getBounds();
        int x = mapPosition.x() - bounds.lowerLeft().x() +1 ;
        int y = bounds.upperRight().y() + 1 - mapPosition.y();
       return new Vector2d(x, y);
    }

    private void drawFrame(Bounds bounds){
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
        IMapElement object = map.objectAt(position);
        if(object == null){
            return;
        }
        Vector2d gridPosition = getGridPosition(position);
        VBox vbox = elementBox.displayObject(object);
        gridPane.add(vbox, gridPosition.x(), gridPosition.y());
    }

    private void drawMap(){
        Bounds bounds = map.getBounds();
        System.out.println(bounds);
        drawFrame(bounds);
        for(int x = bounds.lowerLeft().x(); x <= bounds.upperRight().x(); x++){
            for(int y = bounds.lowerLeft().y(); y <= bounds.upperRight().y(); y++){
                drawObject(new Vector2d(x, y));
            }
        }
    }

    private void updateMap(){


        gridPane.setGridLinesVisible(false);
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);


        drawMap();
    }

    @Override
    public void positionChanged() {
        Platform.runLater(this::updateMap);
    }
}
