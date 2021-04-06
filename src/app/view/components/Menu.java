package app.view.components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * The Menu class is a VBox which allows buttons with actionlisteners to be added to it.
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class Menu extends VBox {
    /**
     * Create a button and set an action listener on the button
     * @param name Name of the button
     * @param var1 The code to be executed upon event trigger
     */
    public void addButton(String name, EventHandler<? super MouseEvent> var1){
        MenuButton button = new MenuButton(name);
        button.setOnMouseClicked(var1);
        getChildren().add(button);
    }
}
