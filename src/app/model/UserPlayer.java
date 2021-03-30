package app.model;

public class UserPlayer extends Player{
    private String username;
    public UserPlayer(String username){
        super(username);
    }

    @Override
    public void doMove(){
        // do move!
    }
}
