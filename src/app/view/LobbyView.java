package app.view;

import app.StateController;
import app.networking.CommandFailedException;
import app.networking.Processor;
import app.networking.ServerNotRespondingException;
import app.state.MainMenuState;
import app.state.games.GameState;
import app.state.games.OthelloState;
import app.users.OnlineOpponent;
import app.users.Player;
import app.users.UserPlayer;
import app.view.gameobjects.Board;
import app.view.gameobjects.OthelloBoard;
import app.view.menucomponents.Menu;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The LobbyView class is used for creating and displaying the components of the lobby
 * @author Sabine Schreuder
 * @version 13-04-21
 */
public class LobbyView implements View {
    private BorderPane view;
    private String game;
    private String username;
    private String[] onlineUsers;
    private VBox playerListBox;
    private VBox headerTextBox;
    private Text headerText;
    private Processor processor;

    public LobbyView(String game, String username, Processor processor){
        this.processor = processor;
        this.game = game;
        this.username = username;

        view = new BorderPane();
        headerTextBox = new VBox();
        headerText = new Text();
        playerListBox = new VBox();

        createLobbyHeader("ONLINE LOBBY FOR " + game.toUpperCase());

        view.setTop(headerTextBox);
        headerTextBox.setAlignment(Pos.CENTER);
        view.setCenter(lobbyMenu());

        if(game.equalsIgnoreCase("TIC-TAC-TOE")){
            view.getStyleClass().add("bg-blue-style");
        }else if (game.equalsIgnoreCase("REVERSI")) {
            view.getStyleClass().add("bg-green-style");
        }
    }

    public void createLobbyHeader(String text){
        if(headerTextBox.getChildren() != null){
            headerTextBox.getChildren().remove(headerText);
        }
        headerTextBox.getChildren().add(headerText);
        headerText.setText(text);
        headerText.setFont(Font.font(30));
    }

    public Menu lobbyMenu(){
        Menu lobbyMenu = new Menu();

        lobbyMenu.addButton("LOOK FOR MATCH", event ->{
            try {
                System.out.println(game);
                processor.subscribe(game);
            } catch (ServerNotRespondingException e) {
                e.printStackTrace();
            } catch (CommandFailedException e) {
                e.printStackTrace();
            }
            enterMatchMaking();
        });
        lobbyMenu.addButton("CHALLENGE OPPONENT", event -> {
            view.getChildren().remove(lobbyMenu);
            createLobbyHeader("ONLINE USERS:");
            view.setCenter(onlineUsersMenu());
        });
        lobbyMenu.addButton("EXIT LOBBY", event -> {
            StateController.setState(new MainMenuState());
        });

        return lobbyMenu;
    }

    public Menu onlineUsersMenu(){
        Menu onlineUsersMenu = new Menu();
        getOnlinePlayerList();
        onlineUsersMenu.addButton("EXIT LOBBY", event -> {
            StateController.setState(new MainMenuState());
        });

        return onlineUsersMenu;
    }

    /**
     * Display a prompt asking if you want to accept or decline a challenge
     * @param challengerUsername The username of the person challenging the user
     */
    public void showChallengeAlert(String challengerUsername, int challengeNumber){
        VBox challengeBox = new VBox();

        Text text = new Text();
        text.setText(challengerUsername + " challenged you to a game of "+ game.toLowerCase() + "! Challenge number: " + challengeNumber);

        HBox buttonBox = new HBox();

        Button acceptButton = new Button("Accept");
        acceptButton.setOnMouseClicked(event ->{
            try {
                processor.setChallengeAccept(challengeNumber);
            } catch (ServerNotRespondingException e) {
                e.printStackTrace();
            } catch (CommandFailedException e) {
                e.printStackTrace();
            }
        });
        Button declineButton = new Button("Decline");
        declineButton.setOnMouseClicked(event ->{
            view.getChildren().remove(challengeBox);
            // send to server challenge declined???
        });

        buttonBox.getChildren().addAll(acceptButton, declineButton);
        challengeBox.getChildren().addAll(text, buttonBox);

        challengeBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.TOP_CENTER);

        view.setBottom(challengeBox);
    }

    /**
     * Retrieve the list of currently online players
     * and add it to onlineUsers
     */
    public void getOnlinePlayerList(){
        try{
            onlineUsers = processor.getPlayerList();
        }catch (ServerNotRespondingException E){
            System.out.println("HELP " + E);
        } catch (CommandFailedException e) {
            e.printStackTrace();
        }

        // test:
        for (String user: onlineUsers) {
            System.out.println(user);
            HBox onlinePlayerButtons = new HBox();

            if (!user.equals(username)) {
                Text onlineUser = new Text(user);
                onlineUser.setFont(Font.font(17));

                Button challengePlayer = new Button("Challenge!");
                challengePlayer.setOnMouseClicked(event2 -> {
                    try {
                        processor.challegengePlayer(user, game);
                    } catch (ServerNotRespondingException e) {
                        e.printStackTrace();
                    } catch (CommandFailedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("challenged " + user + " in a game of " + game);
                });

                onlinePlayerButtons.setAlignment(Pos.CENTER);

//                onlinePlayerButtons.getChildren().addAll(onlineUser, challengePlayer);
                playerListBox.getChildren().add(onlinePlayerButtons);
            }
        }
    }

    public void showChallengeDeclinedAlert(int challengeNumber){
        // show an alert the player has declined the match
    }

    // server calls this when matchmaking OK
    public void enterMatchMaking(){
        // open waiting screen for matchmaking
    }

    public void enterWaitingForOpponent(){
        // open waiting screen for matchmaking
    }

    public void exitWaitingForOpponent(){
        // go back to lobby
        // let player know the opponent did not accept
    }

    // if server starts a game, call this method
    public GameState startMatch(String game, String playerToMove, String opponentUsername){
        Player player1;
        Player player2;
        if(username.equals(playerToMove)){
            player1 = new UserPlayer(username);
            player2 = new OnlineOpponent(opponentUsername);
        }else{
            player1 = new OnlineOpponent(opponentUsername);
            player2 = new UserPlayer(username);
        }

        GameState gameState;
        if(game.equalsIgnoreCase("TIC-TAC-TOE")) {
            gameState = new OthelloState(processor,true,
                    new OthelloBoard(player1, player2));
            StateController.setState(gameState);
            return gameState;
        }else if(game.equalsIgnoreCase("REVERSI")) {
            gameState = new OthelloState(processor,true,
                    new OthelloBoard(player1, player2));
            StateController.setState(gameState);
            return gameState;
        }else{
            System.out.println("Can't start game");
            return null;
        }
    }

    /**
     * Returns the combination of nodes that make up the lobby
     * @return A Parent containing the lobby view
     */
    @Override
    public Parent createView(){
        return view;
    }
}
