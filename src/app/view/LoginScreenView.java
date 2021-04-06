package app.view;

import app.Main;
import app.state.LobbyState;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The LoginScreenView class is used for creating and displaying the components of the login screen
 * @author Sabine Schreuder
 * @version 01/04/21
 */
public class LoginScreenView implements View{
    GridPane view;
    String game;

    /**
     * @param game The game to be played
     */
    public LoginScreenView(String game){
        this.game = game;

        view = new GridPane();
        view.setAlignment(Pos.CENTER);
        view.setHgap(10);
        view.setVgap(10);

        Text loginTitle = new Text("WELCOME TO ONLINE " + game + "!");
        loginTitle.setFont(Font.font(20));
        view.add(loginTitle,0, 0, 2, 1);

        Label userName = new Label("Username:");
        view.add(userName, 0, 1);

        TextField userTextField = new TextField();
        view.add(userTextField,1,1);

        Button btn = new Button("Enter online lobby");
        btn.setOnAction(actionEvent -> {
            if(userTextField.getText()==""){
                Label errorEmptyField = new Label("Please enter a username!");
                view.add(errorEmptyField, 1, 6);
                errorEmptyField.setAlignment(Pos.BOTTOM_CENTER);
            }else {
                Main.setState(new LobbyState(game, userTextField.getText()));
            }
        });

        view.add(btn, 1, 4);
    }

    /**
     * Return the combination of nodes that make up the lobby screen view
     * @return The lobby screen view
     */
    @Override
    public Parent buildSceneGraph() {
        return view;
    }
}
