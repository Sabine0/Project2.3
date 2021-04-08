package app.model;

import app.games.Othello;


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

//    /**
//     * AI calculates move
//     * @return coordinates of the best move
//     */
//    public int[] doMove(){
//        int highestScore = 0;
//        int indexOfHighestScore = 0;
//        for (int i = 0; i < othello.listOfPossibleMoves().size(); i += 3) {
//            if(listOfPossibleMoves().get(i) > highestScore) {
//                highestScore = listOfPossibleMoves().get(i);
//                indexOfHighestScore = i;
//            }
//        }
//        int[] coordinatesBestMove = new int[]{listOfPossibleMoves().get(indexOfHighestScore - 2), listOfPossibleMoves().get(indexOfHighestScore - 1)};
//        return coordinatesBestMove;
//    }


    /**
     * @return False, the class Bot is never human
     */
    @Override
    public boolean isHuman(){
        return false;
    }
}
