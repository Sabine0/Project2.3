package app.state;

import app.state.State;
import javafx.scene.Parent;
import javafx.scene.control.Label;
/**
 * The GameState class is a state in which the current game is being displayed
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public abstract class GameState implements State {
    boolean online;
    boolean playerOneHuman;
    boolean playerTwoHuman;

    /**
     * @param online Boolean indicating if the game is online or not
     * @param playerOneHuman Boolean indicating if player one is human
     * @param playerTwoHuman Boolean indicatingif player two is human
     */
    public GameState(boolean online, boolean playerOneHuman, boolean playerTwoHuman){
        this.online = online;
        this.playerOneHuman = playerOneHuman;
        this.playerTwoHuman = playerTwoHuman;
    }

    /**
     * Code to be executed upon entering the game state
     */
    @Override
    public void enter() {
        System.out.println("entering game");
    }

    /**
     * Code to be executed upon exiting the game state
     */
    @Override
    public void exit() {
        System.out.println("exiting game");
    }

    /**
     *  Launch an online variant of the game
     */
    public abstract void launchOnline();

    /**
     *  Launch a local variant of the game
     */
    public abstract void launchLocal();

    /**
     * Create a new GameState object and get its view
     */
    public abstract Parent getView();
}
