package app.view;

import app.StateController;
import app.networking.Processor;
import app.state.games.OthelloState;
import app.state.games.TicTacToeState;
import app.users.UserPlayer;
import app.view.gameobjects.OthelloBoard;
import app.view.gameobjects.TicTacToeBoard;
import app.view.menucomponents.Menu;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LocalMenuView implements View{
    private BorderPane view;
    private Menu currentMenu;
    private StackPane currentMenuTextBox;
    private Text menuText;
    private String game;
    private boolean online;
    private Processor processor;

    private UserPlayer p1 = new UserPlayer("p1");
    private UserPlayer p2 = new UserPlayer("p2");

    public LocalMenuView(String game, boolean online){
        this.game=game;
        this.online=online;

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
            if(game == "Tic-tac-toe"){
                TicTacToeBoard tttBoard = new TicTacToeBoard(p1, p2, online);
                StateController.setState(new TicTacToeState(processor, tttBoard));
            }
            else{
                OthelloBoard oBoard = new OthelloBoard(p1, p2, online);
                StateController.setState(new OthelloState(processor, oBoard));
            }
        });
        startMenu.addButton("EXIT", event->{
            System.exit(0);
        });
        return startMenu;
    }

    /**
     * @return A parent containing the view for the respective state
     */
    @Override
    public Parent createView() {
        return view;
    }
}
