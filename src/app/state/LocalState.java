package app.state;

import app.view.LocalMenuView;
import app.view.MainMenuView;
import javafx.scene.Parent;

public class LocalState extends State{
    String game;
    boolean online;

    public LocalState(String game, boolean online){
        this.game=game;
        this.online=online;
    }

    /**
     * Code to be executed upon entering the login screen state
     */
    @Override
    public void enter() {
        System.out.println("Entering local menu");
    }

    /**
     * Code to be executed upon exiting the login screen state
     */
    @Override
    public void exit() {
        System.out.println("Exiting local menu");
    }

    /**
     * Create a new MainMenuView object and get its view
     * @return the view of a new MainMenuView object
     */
    @Override
    public Parent getView() {
        return new LocalMenuView(game, online).createView();
    }
}
