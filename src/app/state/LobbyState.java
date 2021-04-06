package app.state;

import app.view.LobbyView;
import javafx.scene.Parent;
import app.networking.*;

/**
 * The LobbyState class is the state in which the lobby is shown
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class LobbyState implements State {
    private String game;
    private String username;
    private Processor processor;

    /**
     * @param game The game to be played
     * @param username The users username
     */
    public LobbyState(String game, String username){
        this.game = game;
        this.username = username;

        // start server connection!
        Connection connection = new Connection();
        connection.connect("145.33.225.170", 7789);
        processor = new Processor(connection);
    }

    /**
     * Code to be executed upon entering the lobby state
     */
    @Override
    public void enter() {
        System.out.println("entering lobby");
        System.out.println("User: " + username);
    }

    /**
     * Code to be executed upon leaving the lobby state
     */
    @Override
    public void exit() {
        System.out.println("exiting lobby");
    }

    /**
     * Create a new LobbyView object and get its view
     * @return the view of a new LobbyView object
     */
    @Override
    public Parent getView() {
        return new LobbyView(game, username, processor).buildSceneGraph();
    }

}
