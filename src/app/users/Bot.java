package app.users;

import app.view.gameobjects.Board;

/**
 * The Bot class holds the attributes of the bot user
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public class Bot extends Player{
    /**
     * @param username The Bot's username
     */
    public Bot(String username){
        super(username);
    }

    @Override
    public int[] getMove(Board board){
        System.out.println("bot p1 go brr");

        // TO DO: implement
        int[] array = new int[2];
        array[0] = 2;
        array[1] = 1;

        return array;
    }
    
    /**
     * @return False, the class Bot is never human
     */
    @Override
    public boolean isHuman(){
        return false;
    }
}
