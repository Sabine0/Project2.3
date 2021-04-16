package app.state;

import app.networking.Processor;
import app.view.MainMenuView;
import javafx.scene.Parent;

/**
 * The MainMenuState class is the state in which the main menu is shown
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class MainMenuState extends State {
    Processor processor;

    public MainMenuState(){
    }

    /**
     * Code to be executed upon entering the login screen state
     */
    @Override
    public void enter() {
        System.out.println("entering main menu");
    }

    /**
     * Code to be executed upon exiting the login screen state
     */
    @Override
    public void exit() {
        System.out.println("exiting main menu");
    }

    /**
     * Create a new MainMenuView object and get its view
     * @return the view of a new MainMenuView object
     */
    @Override
    public Parent getView() {
        return new MainMenuView().createView(); // param processor?
    }
}
