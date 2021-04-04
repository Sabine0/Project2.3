package app.state;

import app.state.State;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public abstract class GameState implements State {
    boolean online;
    boolean playerOneHuman;
    boolean playerTwoHuman;

    public GameState(boolean online, boolean playerOneHuman, boolean playerTwoHuman){
        this.online = online;
        this.playerOneHuman = playerOneHuman;
        this.playerTwoHuman = playerTwoHuman;
    }

    @Override
    public void enter() {
        System.out.println("entering game");
    }

    @Override
    public void exit() {
        System.out.println("exiting game state");
    }

    public abstract void launchOnline();

    public abstract void launchLocal();

    public abstract Parent getView();
}
