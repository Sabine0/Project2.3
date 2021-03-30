package app.state;

import app.view.LobbyView;
import app.view.MainMenuView;
import javafx.scene.Parent;

public class LobbyState implements State {
    @Override
    public void enter() {
        System.out.println("entering lobby");
    }

    @Override
    public void exit() {
        System.out.println("exiting lobby");
    }

    @Override
    public Parent getView() {
        return new LobbyView().buildSceneGraph();
    }

}
