package app.state.games;

import app.networking.Processor;
import app.view.gameobjects.Board;
import app.view.gameobjects.OthelloBoard;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
        // if board
        return false;
    }

    /**
     * @return Boolean if the game has been won by someone
     */
    public ArrayList<Object> calcWinner(){
        ArrayList<Object> listWinner = new ArrayList<>();
        int countBlack = 0;
        int countWhite = 0;
        String winner;
        for(int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if(getBoard().getTile(col, row).getContent() == Color.BLACK) {
                    countBlack++;
                }
                if (getBoard().getTile(col, row).getContent() == Color.WHITE) {
                    countWhite++;
                }
            }
        }
        if (countBlack > countWhite) {
            winner = getP1().getUsername();
            listWinner.add(winner);
            listWinner.add(countBlack);
        } else if (countBlack < countWhite) {
            winner = getP2().getUsername();
            listWinner.add(winner);
            listWinner.add(countWhite);
        } else {
            winner = "Tie!";
            listWinner.add(winner);
        }
        return listWinner;
    }

    /**
     * @return the view of a new Othello object
     */
    @Override
    public Parent getView(){
        return getBoard().boardView();
    }
}
