package app.view;

import app.Main;
import app.games.*;
import app.state.LobbyState;
import app.state.MainMenuState;
import javafx.scene.Parent;
import app.view.components.Menu;
import javafx.scene.layout.StackPane;

public class MainMenuView implements View{
    StackPane view;
    Menu currentMenu;
    boolean multiplayer = false;
    String game;

    public MainMenuView(){
        view = new StackPane();
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

        menu.addButton("CREDITS", event ->{
            // Show credits
        });

        menu.addButton("EXIT", event ->{
            System.exit(0);
        });

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
            Main.setState(new LobbyState());
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
                Main.setState(new TicTacToe(multiplayer, false, true));
            } else if (game == "OTHELLO") {
                Main.setState(new Othello(multiplayer, false, true));
            } else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("PLAYER VS AI", event ->{
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(multiplayer, false, true));
            }else if(game == "OTHELLO"){
                Main.setState(new Othello(multiplayer, false, true));
            }else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("AI VS AI", event ->{
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(multiplayer, false, false));
            }else if(game == "OTHELLO"){
                Main.setState(new Othello(multiplayer, false, false));
            }else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("BACK", event ->{
            setMenu(connectionMenu());
        });

        return menu;
    }

    @Override
    public Parent buildSceneGraph() {
        return view;
    }
}
