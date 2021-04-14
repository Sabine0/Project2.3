package app.users;

import app.view.gameobjects.Board;

/**
 * The Bot class holds the attributes of a player
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public abstract class Player {
    private String username;
    private boolean isPlayer1;
    private boolean isHuman;

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
    public boolean isHuman(){
        return isHuman;
    }

    public void setIsHuman(boolean bool){
        isHuman = bool;
    }

    public boolean isPlayer1(){
        return isPlayer1;
    }

    public void setIsPlayer1(boolean bool){
        isPlayer1 = bool;
    }

}
