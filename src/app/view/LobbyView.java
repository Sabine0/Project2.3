package app.view;

import app.Main;
import app.games.Othello;
import app.games.TicTacToe;
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

import java.util.ArrayList;

public class LobbyView implements View{
    String username;
    BorderPane view;
    String game;
    Menu lobbyMenu;
    Text lobbyText;
    Text userText;
    VBox textBox;
    VBox playerList;
    ArrayList<String> onlineUsers;

    public LobbyView(String game, String username){
        view = new BorderPane();
        this.username = username;
        this.game = game;
        onlineUsers = new ArrayList<>();
        textBox = new VBox();
        lobbyText = new Text();
        userText = new Text();
        lobbyMenu = lobbyMenu();

        // test users for testing array
        onlineUsers.add("Bas");
        onlineUsers.add("Frankie");
        onlineUsers.add("Jonas");

        lobbyText.setText("ONLINE LOBBY FOR " + game);
        lobbyText.setFont(Font.font(30));

        userText.setText("CURRENT USER: " + username);
        userText.setFont(Font.font(30));

        textBox.getChildren().addAll(lobbyText, userText);

        view.setTop(textBox);
        textBox.setAlignment(Pos.CENTER);
        view.setCenter(lobbyMenu);

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

            // TO DO: Get list of available players from server connection (ArrayList)
            for(int i=0; i<onlineUsers.size(); i++) {
                HBox onlinePlayerButtons = new HBox();

                String user = onlineUsers.get(i);
                Text onlineUser = new Text(user);
                onlineUser.setFont(Font.font(17));

                Button challengePlayer = new Button("Challenge!");
                challengePlayer.setOnMouseClicked(event2 ->{
                    // TO DO: challenge as bot or player
                    // TO DO: send challenge to user
                    System.out.println("challenged player: " + user);
                });

                onlinePlayerButtons.setAlignment(Pos.CENTER);

                onlinePlayerButtons.getChildren().addAll(onlineUser, challengePlayer);
                playerList.getChildren().add(onlinePlayerButtons);
            }

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

    @Override
    public Parent buildSceneGraph() {
        return view;
    }

    public void showChallengeAlert(String challengerUsername){
        VBox challengeBox = new VBox();

        Text text = new Text();
        text.setText(challengerUsername + " challenged you!");

        HBox buttonBox = new HBox();

        Button acceptButton = new Button("Accept");
        acceptButton.setOnMouseClicked(event ->{
            // Start online game vs challenger
            if (game == "TICTACTOE") {
                Main.setState(new TicTacToe(true,true, true));
            } else if (game == "OTHELLO") {
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
}
