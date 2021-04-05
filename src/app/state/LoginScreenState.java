package app.state;

import app.view.LoginScreenView;
import javafx.scene.Parent;

public class LoginScreenState implements State{
    String game;

    public LoginScreenState(String game){
        this.game = game;
    }

    @Override
    public void enter() {
        System.out.println("entering login screen");
    }

    @Override
    public void exit() {
        System.out.println("exiting login screen");
    }

    @Override
    public Parent getView() {
        return new LoginScreenView(game).buildSceneGraph();
    }
}
