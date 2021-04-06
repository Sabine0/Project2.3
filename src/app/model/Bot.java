package app.model;

/**
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class Bot extends Player{
    /**
     * @param username The Bot's username
     */
    public Bot(String username){
        super(username);
    }

    /**
     * AI calculates move
     */
    // TO DO: implement
    @Override
    public void doMove(){
        // do move!
    }

    /**
     * @return False, the class Bot is never human
     */
    @Override
    public boolean isHuman(){
        return false;
    }
}
