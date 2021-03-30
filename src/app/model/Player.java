package app.model;

public abstract class Player {
    private String username;
    public Player(String username){
        this.username = username;
    }
    public abstract void doMove();
}
