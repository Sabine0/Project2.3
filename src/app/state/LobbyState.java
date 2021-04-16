package app.state;

import app.networking.CommandFailedException;
import app.networking.Connection;
import app.networking.Processor;
import app.networking.ServerNotRespondingException;
import app.view.LobbyView;
import javafx.scene.Parent;

/**
 * The LobbyState class is the state in which the lobby is shown
 * @author Sabine Schreuder
 * @version 14-04-21
 */
public class LobbyState extends State {
    private String game;
    private String username;
    private Processor processor;
    private boolean appPlayerHuman;

    public LobbyState(String game, boolean appPlayerHuman, String username, String ip, int port) throws ServerNotRespondingException, CommandFailedException {
        this.game = game;
        this.username = username;
        this.appPlayerHuman = appPlayerHuman;

        // Start connection
        Connection connection = new Connection();
        connection.connect(ip, port); // "145.33.225.170"   7789
        processor = new Processor(connection);

        LobbyView lobbyView = new LobbyView(game, appPlayerHuman, username, processor);
        processor.setLobbyView(lobbyView);
        processor.login(username);
    }

    /**
     * Code to be executed upon entering the lobby state
     */
    @Override
    public void enter(){
        System.out.println("Entering lobby for " + game + " as user " + username);
    }

    /**
     * Code to be executed upon exiting the lobby state
     */
    @Override
    public void exit(){
        System.out.println("exiting lobby");
    }

    @Override
    public Parent getView(){
        return new LobbyView(game, appPlayerHuman, username, processor).createView();
    }

}
