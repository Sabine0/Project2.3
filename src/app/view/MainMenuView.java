package app.view;

import app.StateController;
import app.state.LoginScreenState;
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
 * @version 14-04-21
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
            setMenu(createOnlineMenu(), "CHOOSE A GAME");
        });
        startMenu.addButton("EXIT", event->{
            System.exit(0);
        });
        return startMenu;
    }


    /**
     * @param game The game to be played
     * @return The menu containing the game modes for the game
     */
    public Menu createModeMenu(String game){
        Menu modeMenu = new Menu();
        modeMenu.addButton("PLAY AS HUMAN PLAYER", event ->{
            StateController.setState(new LoginScreenState(game, true));
        });
        modeMenu.addButton("PLAY AS AI", event ->{
            StateController.setState(new LoginScreenState(game, false));
        });
        modeMenu.addButton("BACK", event ->{
            setMenu(createOnlineMenu(),"WELCOME");
        });
        return modeMenu;
    }

    /**
     * @return The menu containing all games that are available for online
     */
    public Menu createOnlineMenu() {
        Menu onlineMenu = new Menu();
        // getgamelist from server?
//        String[] gameList = processor.getGamelist();
        String[] gameList = {"Tic-tac-toe", "Reversi"};
        for (String serverGame : gameList) {
            onlineMenu.addButton(serverGame.toUpperCase(), event ->{
                setMenu(createModeMenu(serverGame), "ONLINE "+ serverGame.toUpperCase());
            });
        }
        onlineMenu.addButton("BACK", event ->{
            setMenu(createStartMenu(),"WELCOME");
        });
        return onlineMenu;
    }

    /**
     * Returns the combination of nodes that make up the main menu
     * @return A Parent containing the main menu view
     */
    public Parent createView(){
        return view;
    }
}
