package app.state;

import app.view.LobbyView;
import javafx.scene.Parent;
import app.networking.*;


public class LobbyState implements State {
    String game;

    public LobbyState(String game){
        this.game = game;
    }

    @Override
    public void enter() {
        System.out.println("entering lobby");

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
        return new LobbyView(game).buildSceneGraph();
    }

}
