package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static agh.ics.oop.gui.App.CELL_SIZE;


public class GuiElementBox {



    private final boolean showLabel;
    private final Map<String,ImageView> mapElementsView = new HashMap<>();


    public GuiElementBox() {
        this(false);
    }

    public GuiElementBox(boolean showLabel) {
        this.showLabel = showLabel;
    }

    public VBox displayObject(IMapElement element){

        String elementImageSrc = element.getImageSrc();

        //nie wiem czemu to nie działa
//        if(mapElementsView.containsKey(elementImageSrc)){
//            ImageView elementView = mapElementsView.get(elementImageSrc);
//            return generateVbox(element, elementView);
//        }

        Image img;
        try {
            img = new Image(new FileInputStream(elementImageSrc));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ImageView newElement = new ImageView(img);
        newElement.setImage(img);
        mapElementsView.put(elementImageSrc, newElement);

        return generateVbox(element, newElement);

    }

    private VBox generateVbox(IMapElement element, ImageView elementView){

//        bez etykiety wygląda lepiej
        if(showLabel) {
            elementView.setFitHeight(CELL_SIZE - 20);
            elementView.setFitWidth(CELL_SIZE - 20);
            Label label = new Label(element.position().toString());
            VBox vbox = new VBox(elementView, label);
            vbox.setAlignment(Pos.CENTER);
            return vbox;
        }

        elementView.setFitHeight(CELL_SIZE);
        elementView.setFitWidth(CELL_SIZE);
        return new VBox(elementView);



    }
}
