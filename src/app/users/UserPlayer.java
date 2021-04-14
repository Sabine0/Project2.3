package app.users;

import app.view.gameobjects.Board;

/**
 * The Bot class holds the attributes of the human player
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public class UserPlayer extends Player{

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
        int[] array = {6,9}; // do nothing, just click?!
        return array;
    }

    /**
     * @return True, the class UserPlayer is always human
     */
    @Override
    public boolean isHuman(){
        return true;
    }
}
