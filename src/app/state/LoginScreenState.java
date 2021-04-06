package app.state;

import app.view.LoginScreenView;
import javafx.scene.Parent;

/**
 * The LoginScreenState class is the state in which login screen is shown
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class LoginScreenState implements State{
    String game;

    /**
     * @param game The game to be played
     */
    public LoginScreenState(String game){
        this.game = game;
    }

    /**
     * Code to be executed upon entering the login screen state
     */
    @Override
    public void enter() {
        System.out.println("entering login screen");
    }

    /**
     * Code to be executed upon exiting the login screen state
     */
    @Override
    public void exit() {
        System.out.println("exiting login screen");
    }

    /**
     * Create a new LoginScreenView object and get its view
     * @return the view of a new LoginScreenView object
     */
    @Override
    public Parent getView() {
        return new LoginScreenView(game).buildSceneGraph();
    }
}
