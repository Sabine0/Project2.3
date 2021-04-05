package app.state;

import app.view.LobbyView;
import javafx.scene.Parent;
import app.networking.*;


public class LobbyState implements State {
    private String game;
    private String username;
    private Processor processor;

    public LobbyState(String game, String username){
        this.game = game;
        this.username = username;

        // start server connection!
        Connection connection = new Connection();
        connection.connect("145.33.225.170", 7789);
        processor = new Processor(connection);
    }

    @Override
    public void enter() {
        System.out.println("entering lobby");
        System.out.println("User: " + username);
    }

    @Override
    public void exit() {
        System.out.println("exiting lobby");
    }

    @Override
    public Parent getView() {
        return new LobbyView(game, username, processor).buildSceneGraph();
    }

}
