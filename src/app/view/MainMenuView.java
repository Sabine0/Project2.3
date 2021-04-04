package app.view;

import app.Main;
import app.games.*;
import app.state.LobbyState;
import app.state.MainMenuState;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import app.view.components.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenuView implements View{
    StackPane view;
    Menu currentMenu;
    boolean multiplayer = true;
    String game;

    public MainMenuView(){
        view = new StackPane();

        view.getStyleClass().add("bg-grey-style");
        setMenu(startMenu());
    }

    public void setMenu(Menu menu){
        if(currentMenu!=null){
            view.getChildren().remove(currentMenu);
        }
        view.getChildren().add(menu);
        currentMenu = menu;
    }

    public Menu startMenu(){
        Menu menu = new Menu();
        menu.addButton("PLAY", event ->{
            setMenu(gameMenu());
        });

        menu.addButton("EXIT", event ->{
            System.exit(0);
        });

        return menu;
    }
    public Menu login(){
        Menu menu = new Menu();
        return menu;
    }

    public Menu gameMenu(){
        Menu menu = new Menu();
        menu.addButton("TICTACTOE", event ->{
            game = "TICTACTOE";
            setMenu(connectionMenu());
        });
        menu.addButton("OTHELLO", event ->{
            setMenu(connectionMenu());
            game = "OTHELLO";
        });
        menu.addButton("BACK", event ->{
            setMenu(startMenu());
        });

        return menu;
    }

    public Menu connectionMenu(){
        Menu menu = new Menu();
        menu.addButton("LOCAL", event ->{
            setMenu(modeMenu(game));
        });
        menu.addButton("ONLINE", event ->{
            // TO DO: Redirect to "insert username" form to get username for online matches
            Main.setState(new LobbyState(game));
        });
        menu.addButton("BACK", event ->{
            setMenu(gameMenu());
        });

        return menu;
    }

    public Menu modeMenu(String game){
        Menu menu = new Menu();
        menu.addButton("PLAYER VS PLAYER", event -> {
            if (game == "TICTACTOE") {
                Main.setState(new TicTacToe(false,true, true));
            } else if (game == "OTHELLO") {
                Main.setState(new Othello(false, true, true));
            } else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("PLAYER VS AI", event ->{
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(false, true, false));
            }else if(game == "OTHELLO"){
                Main.setState(new Othello(false, true, false));
            }else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("AI VS AI", event ->{
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(false, false, false));
            }else if(game == "OTHELLO"){
                Main.setState(new Othello(false, false, false));
            }else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("BACK", event ->{
            setMenu(connectionMenu());
        });

        return menu;
    }

    public String getGame(){
        return game;
    }

    @Override
    public Parent buildSceneGraph() {
        return view;
    }
}
