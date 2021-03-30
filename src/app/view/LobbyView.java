package app.view;

import app.Main;
import app.games.Othello;
import app.games.TicTacToe;
import app.state.MainMenuState;
import app.view.components.Menu;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


// whatever the online lobby looks like
public class LobbyView implements View{
    StackPane view;
    Menu currentMenu;

    public LobbyView(){
        view = new StackPane();
        setMenu(modeMenu());
    }

    // While in this lobby you can receive/send game requests
    // Menu: online AI vs AI / Player vs Player

    public void setMenu(Menu menu){
        Text text = new Text(); //temporary
        text.setText("Online lobby");
        if(currentMenu!=null){
            view.getChildren().remove(currentMenu);
        }
        view.getChildren().addAll(text, menu);
        currentMenu = menu;
    }

    public Menu modeMenu(){
        Menu menu = new Menu();
        menu.addButton("PLAYER VS PLAYER", event -> {
            // run online game pvp
        });
        menu.addButton("AI vs AI", event -> {
            // run online game AIvAI
        });
        return menu;
    }

    @Override
    public Parent buildSceneGraph() {
        return view;
    }
}
