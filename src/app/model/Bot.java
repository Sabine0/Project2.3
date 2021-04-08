package app.model;

import java.util.ArrayList;

import app.games.Othello;
import app.games.gameobjects.Tile;

/**
 * @author Luc Willemse
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class Bot extends Player{

    private Othello othello;

    /**
     * @param username The Bot's username
     */
    public Bot(String username){
        super(username);
    }

    /**
     * AI calculates move
     * @return coordinates of the best move
     */
    public int[] doMove(){
        int highestScore = 0;
        int indexOfHighestScore = 0;
        for (int i = 0; i < listOfPossibleMoves().size(); i += 3) {
            if(listOfPossibleMoves().get(i) > highestScore) {
                highestScore = listOfPossibleMoves().get(i);
                indexOfHighestScore = i;
            }
        }
        int[] coordinatesBestMove = new int[]{listOfPossibleMoves().get(indexOfHighestScore - 2), listOfPossibleMoves().get(indexOfHighestScore - 1)};
        return coordinatesBestMove;
    }

    /**
     * mehtodes that give a list of al the possible move with their ratings
     * [col, row, rating, col, row, rating, ...]
     * @return listOfPossibleMoves
     */
    public ArrayList<Integer> listOfPossibleMoves() {
        ArrayList<Integer> listOfPossible = new ArrayList<>();
        
        for(int col = 0; col < 8; col++) {
            for(int row = 0; row < 8; row++) {
                if(othello.isValidMove(col, row)) {
                    othello.setListOfCoordinatesEmpty();
                    listOfPossible.add(col);
                    listOfPossible.add(row);
                    if((col == 0 && row == 0) || (col == 0 && row == 7) ||(col == 7 && row == 0) ||(col == 7 && row == 7)) {
                        listOfPossible.add(5);
                    } else if((col == 0 && row == 1) || (col == 1 && row == 0) || (col == 1 && row == 1) || (col == 0 && row == 6) || (col == 1 && row == 6) || (col == 1 && row == 7) || (col == 6 && row == 0) || (col == 6 && row == 1) || (col == 7 && row == 1) || (col == 6 && row == 7) || (col == 6 && row == 6) || (col == 7 && row == 6)) {
                        listOfPossible.add(1);
                    } else if (col == 0 || col == 7 || row == 0 || row == 7) {
                        listOfPossible.add(4);
                    } else if (col == 1 || col == 6 || row == 1 || row == 6) {
                        listOfPossible.add(2);
                    } else {
                        listOfPossible.add(3);
                    }
                }
            }
        }

        return listOfPossible;
    }

    /**
     * @return False, the class Bot is never human
     */
    @Override
    public boolean isHuman(){
        return false;
    }
}
