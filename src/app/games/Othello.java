package app.games;

import javafx.scene.Parent;
import javafx.scene.control.Label;

public class Othello extends GameState{
    private boolean multiplayer;
    private boolean online;
    private boolean playerOneHuman;

    public Othello(boolean multiplayer, boolean online, boolean playerOneAI) {
        super(8, 8, multiplayer, online);
        this.multiplayer = multiplayer;
        this.online = online;
        this.playerOneHuman = playerOneAI;
    }

    public void launchOnline(boolean multiplayer){
        if(multiplayer){
            // start server connection + run online multiplayer game
        }else{
            if(playerOneHuman){
                // start server connection + run online AI vs AI game
            }else{
                // start server connection + run online player vs AI game
            }
        }
    }

    @Override
    public void launchLocal(boolean multiplayer) {
        if(multiplayer){
            // run local CO-OP game
        }else{
            // run player vs AI game
        }
    }

    @Override
    public Parent getView() {
        // return board
        return new Label("Othello");
    }
}
