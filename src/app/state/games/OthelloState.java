package app.state.games;

import app.networking.Processor;
import app.view.gameobjects.Board;
import app.view.gameobjects.OthelloBoard;
import javafx.scene.Parent;

/**
 * The Othello class holds the game logic for Othello
 * @author Sabine Schreuder
 * @version 12-04-21
 */
// TO DO: implement game logic IN MVC!!!!
public class OthelloState extends GameState{
    public OthelloState(Processor processor, boolean online, String appUserUsername, String opponentUsername,
                        boolean appUserPlayer1, boolean p1Human, boolean p2Human, OthelloBoard othelloBoard){
        super(processor, online, appUserUsername, opponentUsername, appUserPlayer1, p1Human, p2Human, othelloBoard);
    }

    /**
     * @param board The current play board
     * @return boolean if the game has been won
     */
    @Override
    public boolean isWon(Board board) {
        // implement win condition here, return true if the win condition occurred
        return false;
    }

    /**
     * @return the view of a new Othello object
     */
    @Override
    public Parent getView(){
        return getBoard().boardView();
    }
}
