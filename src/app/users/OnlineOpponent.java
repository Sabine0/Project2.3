package app.users;

import app.view.gameobjects.Board;

/**
 * The Bot class holds the attributes of the online opponent
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public class OnlineOpponent extends Player {
    /**
     * @param username The users username
     */
    public OnlineOpponent(String username) {
        super(username);
    }

    @Override
    public int[] getMove(Board board){
        // move comes from server so do nothing?
        System.out.println("online opponent go brr");

        // TO DO: implement
        int[] array = new int[2];
        array[0] = 0;
        array[1] = 0;

        return array;
    }

    @Override
    public boolean isHuman() {
        return false;
    } // doesnt matter
}
