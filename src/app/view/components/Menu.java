package app.view.components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Menu extends VBox {
    public void addButton(String text, EventHandler<? super MouseEvent> var1){
        MenuButton button = new MenuButton(text);
        button.setOnMouseClicked(var1);
        getChildren().add(button);
    }
}
