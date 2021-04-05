package app.state;

import app.view.LobbyView;
import javafx.scene.Parent;
import app.networking.*;


public class LobbyState implements State {
    String game;
    String username;

    public LobbyState(String game, String username){
        this.game = game;
        this.username = username;
    }

    @Override
    public void enter() {
        System.out.println("entering lobby");
        System.out.println("User: " + username);

        // start server connection!
        Connection connection = new Connection();
        connection.connect("localhost", 7789);
        Processor processor = new Processor(connection);
    }

    @Override
    public void exit() {
        System.out.println("exiting lobby");
    }

    @Override
    public Parent getView() {
        return new LobbyView(game, username).buildSceneGraph();
    }

}
