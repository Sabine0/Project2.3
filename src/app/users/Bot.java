package app.users;

import app.view.gameobjects.Board;

import java.util.AbstractSequentialList;
import java.util.ArrayList;

/**
 * The Bot class holds the attributes of the bot user
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public abstract class Bot extends Player{
    /**
     * @param username The Bot's username
     */
    public Bot(String username){
        super(username);
    }

    @Override
    public abstract int[] getMove(Board board);

    /**
     * @return False, the class Bot is never human
     */
    @Override
    public boolean isHuman(){
        return false;
    }
}
