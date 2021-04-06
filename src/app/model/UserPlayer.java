package app.model;

/**
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class UserPlayer extends Player{
    /**
     * @param username The UserPlayer's username
     */
    public UserPlayer(String username){
        super(username);
    }

    // TO DO: implement?
    @Override
    public void doMove(){
        // click the tile
    }

    /**
     * @return True, the class UserPlayer is always human
     */
    public boolean isHuman(){
        return true;
    }
}
