package app.view.menucomponents;

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
     * @param name The Name of the button
     * @param event The code to be executed upon event trigger
     */
    public void addButton(String name, EventHandler<? super MouseEvent> event){
        MenuButton button = new MenuButton(name);
        button.setOnMouseClicked(event);
        getChildren().add(button);
    }
}
