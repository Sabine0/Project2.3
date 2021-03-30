package app.view.components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Menu extends VBox {
    public void addButton(String text, EventHandler<? super MouseEvent> var1){
        Button button = new Button(text);
        button.setOnMouseClicked(var1);
        getChildren().add(button);
    }
}
