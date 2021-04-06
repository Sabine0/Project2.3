package app.view;

import app.Main;
import app.games.Othello;
import app.games.TicTacToe;
import app.networking.CommandFailedException;
import app.networking.Processor;
import app.networking.ServerNotRespondingException;
import app.state.MainMenuState;
import app.view.components.Menu;
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
 * @version 01-04-21
 */
public class LobbyView implements View{
    private String username;
    private BorderPane view;
    private String game;
    private Menu lobbyMenu;
    private Text lobbyText;
    private Text userText;
    private VBox textBox;
    private VBox playerList;
    private String[] onlineUsers;
    private Processor processor;

    /**
     * @param game The game to be played
     * @param username The username of player 1
     * @param processor The processor which holds the connection to the server
     */
    public LobbyView(String game, String username, Processor processor){
        this.processor = processor;
        view = new BorderPane();
        this.username = username;
        this.game = game;
        textBox = new VBox();
        lobbyText = new Text();
        userText = new Text();
        lobbyMenu = lobbyMenu();


        lobbyText.setText("ONLINE LOBBY FOR " + game);
        lobbyText.setFont(Font.font(30));

        userText.setText("CURRENT USER: " + username);
        userText.setFont(Font.font(30));

        textBox.getChildren().addAll(lobbyText, userText);

        view.setTop(textBox);
        textBox.setAlignment(Pos.CENTER);
        view.setCenter(lobbyMenu);
        // tijdelijk gefixed
        try {
            processor.login(username);
        } catch (ServerNotRespondingException e) {
            e.printStackTrace();
        } catch (CommandFailedException e) {
            e.printStackTrace();
        }
    }

    // TO DO: while loop for receiving challenge requests
    public Menu lobbyMenu(){
        Menu menu = new Menu();
        menu.addButton("CHALLENGE OPPONENT", event -> {
            menu.getChildren().remove(0);

            playerList = new VBox();
            Text text = new Text("Online users: ");
            text.setFont(Font.font(20));

            playerList.getChildren().addAll(text);
            playerList.setAlignment(Pos.TOP_CENTER);

            getOnlinePlayerList();

            view.setCenter(playerList);
            view.setBottom(menu);
            showChallengeAlert("Ron"); // only for testing
        });

        menu.addButton("EXIT LOBBY", event -> {
            Main.setState(new MainMenuState());
            username = "";
        });

        showChallengeAlert("Henkie"); // only for testing

        return menu;
    }

    /**
     * Return the combination of nodes that make up the lobby view
     * @return The lobby view
     */
    @Override
    public Parent buildSceneGraph() {
        return view;
    }

    /**
     * Display a prompt asking if you want to accept or decline a challenge
     * @param challengerUsername The username of the person challenging the user
     */
    public void showChallengeAlert(String challengerUsername){
        VBox challengeBox = new VBox();

        Text text = new Text();
        text.setText(challengerUsername + " challenged you!");

        HBox buttonBox = new HBox();

        Button acceptButton = new Button("Accept");
        acceptButton.setOnMouseClicked(event ->{
            // TO DO: Start online game vs challenger
            if (game.equals("TICTACTOE")) {
                Main.setState(new TicTacToe(true,true, true, false, username, challengerUsername)); // challenger always goes first?
            } else if (game.equals("OTHELLO")) {
                Main.setState(new Othello(true, true, true));
            }
        });
        Button declineButton = new Button("Decline");
        declineButton.setOnMouseClicked(event ->{
            view.getChildren().remove(challengeBox);
        });

        buttonBox.getChildren().addAll(acceptButton, declineButton);
        challengeBox.getChildren().addAll(text, buttonBox);

        challengeBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.TOP_CENTER);

        view.setRight(challengeBox);
    }

    /**
     * Retrieve the list of currently online players
     * and add it to onlineUsers
     */
    public void getOnlinePlayerList(){
        // tijdelijk gefixed
        try{
            onlineUsers = processor.getPlayerList();
        }catch (ServerNotRespondingException E){
            System.out.println("HELP " + E);
        } catch (CommandFailedException e) {
            e.printStackTrace();
        }

        // test
        for (String string: onlineUsers) {
            System.out.println(string);
        }

        for(int i=0; i<onlineUsers.length; i++) {
            HBox onlinePlayerButtons = new HBox();

            String user = onlineUsers[i];
            if(!user.equals(username)) {
                Text onlineUser = new Text(user);
                onlineUser.setFont(Font.font(17));

                Button challengePlayer = new Button("Challenge!");
                challengePlayer.setOnMouseClicked(event2 -> {
                    // TO DO: challenge as bot or player
                    // TO DO: send challenge to user
                    System.out.println("challenged player: " + user);
                });

                onlinePlayerButtons.setAlignment(Pos.CENTER);

                onlinePlayerButtons.getChildren().addAll(onlineUser, challengePlayer);
                playerList.getChildren().add(onlinePlayerButtons);
            }
        }
    }
}
