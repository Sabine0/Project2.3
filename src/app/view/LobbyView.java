package app.view;

import app.StateController;
import app.networking.CommandFailedException;
import app.networking.Processor;
import app.networking.ServerNotRespondingException;
import app.state.MainMenuState;
import app.state.games.GameState;
import app.state.games.OthelloState;
import app.state.games.TicTacToeState;
import app.users.*;
import app.view.gameobjects.OthelloBoard;
import app.view.gameobjects.TicTacToeBoard;
import app.view.menucomponents.Menu;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The LobbyView class is used for creating and displaying the components of the lobby
 * @author Sabine Schreuder
 * @version 14-04-21
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
    private boolean appPlayerHuman;

    public LobbyView(String game, boolean appPlayerHuman, String username, Processor processor){
        this.appPlayerHuman = appPlayerHuman;
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
        view.setCenter(createLobbyMenu());

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

    public Menu createLobbyMenu(){
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
            view.setCenter(createOnlineUsersMenu());
        });
        lobbyMenu.addButton("EXIT LOBBY", event -> {
            StateController.setState(new MainMenuState());
        });

        return lobbyMenu;
    }

    public Menu createOnlineUsersMenu(){
        Menu onlineUsersMenu = new Menu();
        createOnlinePlayerList();
        onlineUsersMenu.getChildren().add(playerListBox);
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
            // is there no way to tell the server you declined the challenge?
        });

        buttonBox.getChildren().addAll(acceptButton, declineButton);
        challengeBox.getChildren().addAll(text, buttonBox);

        challengeBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.TOP_CENTER);

        view.setBottom(challengeBox);
    }

    public void createOnlinePlayerList(){
        try{
            onlineUsers = processor.getPlayerList();
        }catch (ServerNotRespondingException E){
            System.out.println("HELP " + E);
        } catch (CommandFailedException e) {
            e.printStackTrace();
        }

        for (int i =0; i<onlineUsers.length; i++) {
            System.out.println(onlineUsers[i]); // test
            HBox onlinePlayersWithButton = new HBox();
            String user = onlineUsers[i];

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
                    enterWaitingForOpponent(user);
                    System.out.println("challenged " + user + " in a game of " + game);
                });

                onlinePlayersWithButton.getChildren().addAll(onlineUser, challengePlayer);
                onlinePlayersWithButton.setAlignment(Pos.CENTER);
            }
            playerListBox.getChildren().add(onlinePlayersWithButton);
        }
    }

    public void showChallengeDeclinedAlert(int challengeNumber){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Could not start the match");
        alert.setHeaderText("The player you challenged declined the match");
        alert.setContentText("Challenge number: " + challengeNumber);
        alert.show();
    }

    public void enterMatchMaking(){
        view.getChildren().remove(1);
        Menu menu = new Menu();
        Text text = new Text("Finding an opponent...");
        text.setFont(Font.font(17));
        menu.setAlignment(Pos.TOP_CENTER);
        menu.getChildren().add(text);
        menu.addButton("BACK", event -> {
            view.getChildren().remove(1);
            view.setCenter(createLobbyMenu());
        });
        view.setCenter(menu);
    }

    public void enterWaitingForOpponent(String opponentUsername){
        view.getChildren().remove(1);
        Menu menu = new Menu();
        Text text = new Text("Waiting for response from " +  opponentUsername);
        text.setFont(Font.font(17));
        menu.setAlignment(Pos.TOP_CENTER);
        menu.getChildren().add(text);
        menu.addButton("BACK", event -> {
            view.getChildren().remove(1);
            view.setCenter(createLobbyMenu());
        });
        view.setCenter(menu);
    }

    public void exitWaitingForOpponent(){
        view.getChildren().remove(1);
        view.setCenter(createLobbyMenu());
    }

    /**
     * if server starts a game, call this method to start the visualisation
     * @param game The game to be played
     * @param playerToMove The name of the player whose turn it is
     * @param opponentUsername The name of the other player
     * @return gameState The game state: othello or tic-tac-toe
     */
    public GameState startMatch(String game, String playerToMove, String opponentUsername){
        System.out.println("online match is starting!"); // test
        Player player1;
        Player player2;

        if(username.equals(playerToMove) && appPlayerHuman){
            player1 = new UserPlayer(username);
            player2 = new OnlineOpponent(opponentUsername);
        }else if(username.equals(playerToMove)){
            if(game.equalsIgnoreCase("TIC-TAC-TOE")) {
                player1 = new TicTacToeAI(username);
            }else{
                player1 = new OthelloAI(username);
            }
            player2 = new OnlineOpponent(opponentUsername);
        }else{
            player1 = new OnlineOpponent(opponentUsername);
            if(game.equalsIgnoreCase("TIC-TAC-TOE")) {
                player2 = new TicTacToeAI(username);
            }else{
                player2 = new OthelloAI(username);
            }
        }

        GameState gameState;
        if(game.equalsIgnoreCase("TIC-TAC-TOE")) {
            gameState = new TicTacToeState(processor, new TicTacToeBoard(player1, player2));
            StateController.setState(gameState);
            return gameState;
        }else if(game.equalsIgnoreCase("REVERSI")) {
            gameState = new OthelloState(processor, new OthelloBoard(player1, player2));
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
