package app.users;

import app.view.gameobjects.Board;

/**
 * The Bot class holds the attributes of the human player
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public class UserPlayer extends Player{
    int[] moves = new int[2];

    /**
     * @param username The UserPlayer's username
     */
    public UserPlayer(String username){
        super(username);
    }

    /**
     * Sets a move on the board
     * @param board The current playboard
     */
    @Override
    public int[] getMove(Board board){
        for(int r = 0; r < board.getBoardSize(); r++) {
            for (int c = 0; c < board.getBoardSize(); c++) {
                int row = r;
                int col = c;
                board.getTile(c,r).setOnMouseClicked(event ->{
                    //TO DO: fix
                    moves[0] = row;
                    moves[1] = col;
                });
            }
        }
        return this.moves;
    }

    /**
     * @return True, the class UserPlayer is always human
     */
    @Override
    public boolean isHuman(){
        return true;
    }
}
