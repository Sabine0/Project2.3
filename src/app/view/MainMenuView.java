package app.view;

import app.Main;
import app.games.*;
import app.state.LoginScreenState;
import app.state.MainMenuState;
import javafx.scene.Parent;
import app.view.components.Menu;
import javafx.scene.layout.StackPane;


/**
 * The MainMenuView class is used for creating and displaying the components of the main menu
 * @author Sabine Schreuder
 * @version 01/04/21
 */
public class MainMenuView implements View{
    StackPane view;
    Menu currentMenu;
    String game;

    public MainMenuView(){
        view = new StackPane();

        view.getStyleClass().add("bg-grey-style");
        setMenu(startMenu());
    }

    /**
     * Set the current menu to the menu given in the parameter
     * @param menu The menu to be set to
     */
    public void setMenu(Menu menu){
        if(currentMenu!=null){
            view.getChildren().remove(currentMenu);
        }
        view.getChildren().add(menu);
        currentMenu = menu;
    }

    /**
     * Create the starting menu and return it
     * @return The start menu
     */
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

    /**
     * Create the game menu and return it
     * @return The menu containing all available games
     */
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

    /**
     * Create the connection menu and return it
     * @return The menu containing all connection options
     */
    public Menu connectionMenu(){
        Menu menu = new Menu();
        menu.addButton("LOCAL", event ->{
            setMenu(modeMenu(game));
        });
        menu.addButton("ONLINE", event ->{
            Main.setState(new LoginScreenState(game));
        });
        menu.addButton("BACK", event ->{
            setMenu(gameMenu());
        });

        return menu;
    }


    /**
     * Create the mode menu and return it
     * @param game The game to be played
     * @return The menu containing all mode options
     */
    public Menu modeMenu(String game){
        Menu menu = new Menu();
        menu.addButton("PLAYER VS PLAYER", event -> {
            if (game == "TICTACTOE") {
                Main.setState(new TicTacToe(false,true, true, true, "Player 1", "Player 2"));
            } else if (game == "OTHELLO") {
                Main.setState(new Othello(false, true, true, true, "Player 1", "Player 2"));
            } else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("PLAYER VS AI", event ->{
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(false, true, false, true,"Player1", "Victor BOT"));
            }else if(game == "OTHELLO"){
                Main.setState(new Othello(false, true, false,true, "Player 1","Victor BOT"));
            }else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("AI VS AI", event ->{
            if(game == "TICTACTOE"){
                Main.setState(new TicTacToe(false, false, false, true, "Jean BOT", "Victor BOT"));
            }else if(game == "OTHELLO"){
                Main.setState(new Othello(false, false, false, true, "Jean BOT","Victor BOT"));
            }else {
                Main.setState(new MainMenuState());
            }
        });
        menu.addButton("BACK", event ->{
            setMenu(connectionMenu());
        });

        return menu;
    }

    /**
     * Return the combination of nodes that make up the main menu view
     * @return The main menu view
     */
    @Override
    public Parent buildSceneGraph() {
        return view;
    }
}
