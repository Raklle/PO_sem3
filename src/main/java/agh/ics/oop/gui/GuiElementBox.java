package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;

import static agh.ics.oop.gui.App.CELL_SIZE;


public class GuiElementBox {


    private final IMapElement element;
    private final boolean showLabel;


    public GuiElementBox(IMapElement element) {
        this(element,true);
    }
    public GuiElementBox(IMapElement element, boolean showLabel) {
        this.element = element;
        this.showLabel = showLabel;
    }

    public VBox displayObject(){
        Image img = new Image(element.getImageSrc());
        ImageView grassView = new ImageView(img);
        grassView.setImage(img);
        VBox vbox = new VBox(0);
//        bez etykiety wygląda lepiej
        if(showLabel) {
            grassView.setFitHeight(CELL_SIZE - 20);
            grassView.setFitWidth(CELL_SIZE - 20);
            Label label = new Label(element.position().toString());
            vbox.getChildren().addAll(grassView, label);
            vbox.setAlignment(Pos.CENTER);
        }else{
            grassView.setFitHeight(CELL_SIZE);
            grassView.setFitWidth(CELL_SIZE);
            vbox.getChildren().addAll(grassView);
        }
        return vbox;

    }
}
