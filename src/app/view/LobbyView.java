package app.view;

import app.Main;
import app.games.Othello;
import app.games.TicTacToe;
import app.state.MainMenuState;
import app.view.components.Menu;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


// whatever the online lobby looks like
public class LobbyView implements View{
    BorderPane view;
    String game;
    Menu modeMenu;
    Text lobbyText;
    HBox textBox;

    public LobbyView(String game){
        this.game = game;
        view = new BorderPane();
        textBox = new HBox();
        lobbyText = new Text();
        modeMenu = modeMenu();

        lobbyText.setText("ONLINE LOBBY FOR " + game);
        textBox.getChildren().addAll(lobbyText);

        view.setTop(textBox);
        view.setCenter(modeMenu);
    }

    // While in this lobby you can receive/send game requests
    // Menu: online AI vs AI / Player vs Player

    public Menu modeMenu(){
        Menu menu = new Menu();
        menu.addButton("PLAYER vs PLAYER", event -> {
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(true, true, true));
            }else if(game == "OTHELLO") {
                Main.setState(new Othello(true, true, true));
            }
        });
        menu.addButton("AI vs AI", event -> {
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(true, false, false));
            }else if(game == "OTHELLO") {
                Main.setState(new Othello(true, false, false));
            }
        });
        menu.addButton("EXIT LOBBY", event -> {
            Main.setState(new MainMenuState());
        });
        return menu;
    }

    @Override
    public Parent buildSceneGraph() {
        return view;
    }
}
