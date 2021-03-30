package app.games;

import app.state.State;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public abstract class GameState implements State {
    boolean multiplayer;
    boolean online;

    public GameState(int x, int y, boolean multiplayer, boolean online){
        this.multiplayer = multiplayer;
        this.online = online;

    }

    @Override
    public void enter() {
        System.out.println("entering tictactoe");
        if(online){
            launchOnline(multiplayer);
        }else {
            launchLocal(multiplayer);
        }
    }

    @Override
    public void exit() {
        System.out.println("exiting tictactoe");
    }

    public abstract void launchOnline(boolean multiplayer);

    public abstract void launchLocal(boolean multiplayer);



    @Override
    public Parent getView() {
        Label label = new Label("haha");
        return label;
    }
}
