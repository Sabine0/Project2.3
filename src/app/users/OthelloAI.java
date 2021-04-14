package app.users;

import app.view.gameobjects.Board;

import java.util.ArrayList;

/**
 * The OthelloAI class holds the attributes of the othello algorithm
 * @author Sabine Schreuder
 * @author Luc
 * @version 13-04-21
 */
public class OthelloAI extends Bot{
    public OthelloAI(String username){
        super(username);
    }

    @Override
    public int[] getMove(Board board){
        ArrayList<Integer> listOfPossible = new ArrayList<>();
        for(int col = 0; col < board.getBoardSize(); col++) {
            for(int row = 0; row < board.getBoardSize(); row++) {
                if(board.isValidMove(col, row)) {
                    //setListOfCoordinatesEmpty();
                    listOfPossible.add(col);
                    listOfPossible.add(row);
                    if((col == 0 && row == 0) || (col == 0 && row == 7) ||(col == 7 && row == 0) ||(col == 7 && row == 7)) {
                        listOfPossible.add(5);
                    } else if((col == 0 && row == 1) || (col == 1 && row == 0) || (col == 1 && row == 1) || (col == 0 && row == 6)
                            || (col == 1 && row == 6) || (col == 1 && row == 7) || (col == 6 && row == 0) || (col == 6 && row == 1)
                            || (col == 7 && row == 1) || (col == 6 && row == 7) || (col == 6 && row == 6) || (col == 7 && row == 6)) {
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

        int highestScore = 0;
        int indexOfHighestScore = 0;
        for (int i = 2; i < listOfPossible.size(); i += 3) {
            if(listOfPossible.get(i) > highestScore) {
                highestScore = listOfPossible.get(i);
                indexOfHighestScore = i;
            }
        }
        int[] coordinatesBestMove = new int[]{listOfPossible.get(indexOfHighestScore - 2), listOfPossible.get(indexOfHighestScore - 1)};
        return coordinatesBestMove;
    }

}