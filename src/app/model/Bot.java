package app.model;

import app.games.Othello;
import app.games.gameobjects.Tile;

/**
 * @author Luc Willemse
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class Bot extends Player{
    /**
     * @param username The Bot's username
     */
    public Bot(String username){
        super(username);
    }

    /**
     * AI calculates move
     */
    // TO DO: implement
    public void doMove(Tile playing, Tile tegenStander){
//        int[] listOfMoves = new int[]{};
//        int teller = 0;
//        Othello othello = new Othello();
//        for(int c = 1; c < 9; c++) {
//            for(int r = 1; r < 9; r++) {
//                if(othello.validMove(c, r, playing, tegenStander)) {
//                    listOfMoves[teller] = c;
//                    teller++;
//                    listOfMoves[teller] = r;
//                    teller++;
//                }
//            }
//        }
    }

    /**
     * @return False, the class Bot is never human
     */
    @Override
    public boolean isHuman(){
        return false;
    }
}
