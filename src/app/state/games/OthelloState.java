package app.state.games;

import app.networking.Processor;
import app.view.gameobjects.OthelloBoard;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * The Othello class holds the game logic for Othello
 * @author Sabine Schreuder
 * @author Luc Willemse
 * @version 14-04-21
 */
public class OthelloState extends GameState{
    public OthelloState(Processor processor, OthelloBoard othelloBoard){
        super(processor, othelloBoard);
    }

    /**
     * @return ArrayList winner, score
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
            winner = getBoard().getP1().getUsername();
            listWinner.add(winner);
            listWinner.add(countBlack);
        } else if (countBlack < countWhite) {
            winner = getBoard().getP2().getUsername();
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
