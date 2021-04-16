package app.users;

import app.view.gameobjects.Board;

/**
 * The Bot class holds the attributes of a player
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public abstract class Player {
    private String username;

    /**
     * @param username The users username
     */
    public Player(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public abstract int[] getMove(Board board);

    /**
     * @return Boolean if user is human
     */
    public abstract boolean isHuman();

}
