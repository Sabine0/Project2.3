package app.model;

public class OnlineOpponent extends Player{

    /**
     * @param username The users username
     */
    public OnlineOpponent(String username) {
        super(username);
    }

    public void doMove(){
        //?
    }
    @Override
    public boolean isHuman() {
        return true;
    }
}
