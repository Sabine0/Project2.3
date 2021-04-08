package app.model;

/**
 * @author Sabine Schreuder
 * @version 01-04-21
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

//    public abstract void doMove();
    /**
     * @return Boolean if user is human
     */
    public abstract boolean isHuman();
}
