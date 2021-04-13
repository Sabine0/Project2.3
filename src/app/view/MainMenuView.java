package app.view;

import app.StateController;
import app.networking.CommandFailedException;
import app.networking.ServerNotRespondingException;
import app.view.gameobjects.OthelloBoard;
import app.view.gameobjects.TicTacToeBoard;
import app.state.LoginScreenState;
import app.state.games.OthelloState;
import app.state.games.TicTacToeState;
import app.view.menucomponents.Menu;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The MainMenuView class is used for creating and displaying the components of the main menu
 * @author Sabine Schreuder
 * @version 13-04-21
 */
public class MainMenuView implements View {
    private BorderPane view;
    private Menu currentMenu;
    private StackPane currentMenuTextBox;
    private Text menuText;
    private String game;
//    private Processor processor;

    /**
//     * @param processor The connections processor
     */
    public MainMenuView(){ // param processor for getgamelist?
//        this.processor = processor;

        // Styling goes here
        view = new BorderPane();
        currentMenuTextBox = new StackPane();
        menuText = new Text();

        view.getStyleClass().add("bg-grey-style");
        setMenu(createStartMenu(), "WELCOME!");
    }

    /**
     * @param menu The menu to be set
     * @param textString The text to be displayed at the top of the menu
     */
    public void setMenu(Menu menu, String textString){
        if(currentMenu!=null){
            view.getChildren().remove(currentMenu);
        } else if(currentMenuTextBox !=null){
            view.getChildren().remove(currentMenuTextBox);
        }

        currentMenu = menu;
        menuText.setText(textString);
        menuText.setFont(Font.font(50));
        menuText.setFill(Color.WHITE);

        StackPane menuTextBox = new StackPane();
        menuTextBox.getChildren().add(menuText);

        view.setTop(menuTextBox);
        view.setCenter(menu);
    }

    /**
     * @return The initial menu
     */
    public Menu createStartMenu(){
        Menu startMenu = new Menu();
        startMenu.addButton("PLAY", event->{
            setMenu(createConnectionMenu(), "PICK A CONNECTION");
        });
        startMenu.addButton("EXIT", event->{
            System.exit(0);
        });
        return startMenu;
    }

    /**
     * @return The menu containing connection options
     */
    public Menu createConnectionMenu(){
        Menu connectionMenu = new Menu();
        connectionMenu.addButton("LOCAL", event->{
            setMenu(createLocalMenu(),"LOCAL PLAY");
        });
        connectionMenu.addButton("ONLINE", event->{
            try {
                setMenu(createOnlineMenu(), "ONLINE PLAY");
            } catch (ServerNotRespondingException e) {
                e.printStackTrace();
            } catch (CommandFailedException e) {
                e.printStackTrace();
            }
        });
        connectionMenu.addButton("BACK", event ->{
            setMenu(createStartMenu(),"WELCOME");
        });
        return connectionMenu;
    }

    /**
     * @return The menu containing the locally available games
     */
    public Menu createLocalMenu(){
        Menu localMenu = new Menu();
        localMenu.addButton("TICTACTOE", event ->{
            game = "TIC-TAC-TOE";
            setMenu(createModeMenu(game), "SELECT A MODE");
        });
        localMenu.addButton("OTHELLO", event ->{
            game = "OTHELLO";
            setMenu(createModeMenu(game), "SELECT A MODE");
        });
        localMenu.addButton("BACK", event ->{
            setMenu(createConnectionMenu(),"PICK A CONNECTION");
        });

        return localMenu;
    }

    /**
     * @param game The game to be played
     * @return The menu containing the game modes for the game
     */
    public Menu createModeMenu(String game){
        Menu modeMenu = new Menu();
        modeMenu.addButton("PLAYER vs PLAYER", event ->{
            enterGameState(game, "Player 1", "Player 2", true, true);
        });
        modeMenu.addButton("PLAYER vs AI", event ->{
            enterGameState(game, "Player 1", "Victor BOT", true, false);
        });
        modeMenu.addButton("AI vs AI", event ->{
            enterGameState(game, "Jean BOT", "Victor BOT", false, false);
        });
        modeMenu.addButton("BACK", event ->{
            setMenu(createLocalMenu(),"LOCAL PLAY");
        });
        return modeMenu;
    }

    /**
     * @return The menu containing all games that are available for online
     */
    public Menu createOnlineMenu() throws ServerNotRespondingException, CommandFailedException {
        Menu onlineMenu = new Menu();
        // getgamelist from server?
//        String[] gameList = processor.getGamelist();
        String[] gameList = {"Tic-tac-toe", "Othello"};
        for (String serverGame : gameList) {
//            System.out.println(serverGame);
            onlineMenu.addButton(serverGame.toUpperCase(), event ->{
                if(serverGame.equalsIgnoreCase("Tic-tac-toe")){
                    StateController.setState(new LoginScreenState(serverGame)); // param processor?
                } else if(serverGame.equalsIgnoreCase("Othello")){
                    StateController.setState(new LoginScreenState(serverGame)); // param processor?
                }else{
                    System.out.println("This game is currently not available");
                }
            });
        }

        onlineMenu.addButton("BACK", event ->{
            setMenu(createConnectionMenu(),"PICK A CONNECTION");
        });
        return onlineMenu;
    }

    /**
     * @param game The name of the game state to be entered
     */
    public void enterGameState(String game, String appUserUsername, String opponentUsername, boolean p1Human, boolean p2Human){
        if(game.equalsIgnoreCase("TIC-TAC-TOE")){
            StateController.setState(new TicTacToeState(null,false, appUserUsername, opponentUsername,
                    true, p1Human, p2Human, new TicTacToeBoard()));
            System.out.println("ttt game started");
        }else if(game.equalsIgnoreCase("OTHELLO")){
            StateController.setState(new OthelloState(null, false, appUserUsername, opponentUsername,
                    true, p1Human, p2Human, new OthelloBoard()));
            System.out.println("othello game started");
        }else{
            System.out.println("Game not found");
        }
    }

    /**
     * Returns the combination of nodes that make up the main menu
     * @return A Parent containing the main menu view
     */
    public Parent createView(){
        return view;
    }

}
