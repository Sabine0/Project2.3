package app.state;

import app.Main;
import app.view.MainMenuView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenuState implements State {

    @Override
    public void enter() {
        System.out.println("entering main menu");
    }

    @Override
    public void exit() {
        System.out.println("exiting main menu");
    }

    @Override
    public Parent getView() {
        return new MainMenuView().buildSceneGraph();
    }
}
