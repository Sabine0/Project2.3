package app.view;

import app.StateController;
import app.networking.CommandFailedException;
import app.networking.Processor;
import app.networking.ServerNotRespondingException;
import app.state.LobbyState;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The LoginScreenView class is used for creating and displaying the components of the login screen
 * @author Sabine Schreuder
 * @version 13/04/21
 */
public class LoginScreenView implements View{
    private GridPane view;
    private String game;

    /**
     * @param game The game to be played
     */
    public LoginScreenView(String game){
        // Styling goes here
        this.game = game;

        view = new GridPane();
        view.setAlignment(Pos.CENTER);
        view.setHgap(10);
        view.setVgap(10);

        Text loginTitle = new Text("WELCOME TO ONLINE " + this.game.toUpperCase() + "!");
        loginTitle.setFont(Font.font(20));
        view.add(loginTitle,0, 0, 2, 1);

        Label userName = new Label("Enter a username:");
        view.add(userName, 0, 1);

        TextField userTextField = new TextField();
        view.add(userTextField,1,1);

        Button btn = new Button("Enter online lobby");
        btn.setOnAction(actionEvent -> {
            if(userTextField.getText()==""){
                Label errorEmptyField = new Label("You cannot join the lobby \n without a username!!");
                view.add(errorEmptyField, 1, 6);
                errorEmptyField.setAlignment(Pos.BOTTOM_CENTER);
            }else {
                try {
                    StateController.setState(new LobbyState(game, userTextField.getText()));
                } catch (ServerNotRespondingException e) {
                    e.printStackTrace();
                } catch (CommandFailedException e) {
                    e.printStackTrace();
                }
            }
        });

        view.add(btn, 1, 4);

        if(game.equalsIgnoreCase("TIC-TAC-TOE")){
            view.getStyleClass().add("bg-blue-style");
        }else if (game.equalsIgnoreCase("OTHELLO")) {
            view.getStyleClass().add("bg-green-style");
        }
    }

    /**
     * Returns the combination of nodes that make up the login screen
     * @return A Parent containing the login screen view
     */
    @Override
    public Parent createView() {
        return view;
    }
}
